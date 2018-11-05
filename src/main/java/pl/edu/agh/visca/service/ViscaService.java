package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.AddressCmd;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.PanTiltHomeCmd;
import pl.edu.agh.visca.model.Constants;

import java.util.List;

@Service
public class ViscaService {

    private final String serialPortName;
    private final int serialBaudrate;
    private final int serialDatabits;
    private final int serialStopBits;
    private final int serialParity;

    private SerialPort serialPort;

    public ViscaService(@Value("${serial.port}") String serialPortName,
                        @Value("${serial.baudrate}") int serialBaudrate,
                        @Value("${serial.databits}") int serialDatabits,
                        @Value("${serial.stopbits}") int serialStopBits,
                        @Value("${serial.parity}") int serialParity) throws SerialPortException {

        this.serialPortName = serialPortName;
        this.serialBaudrate = serialBaudrate;
        this.serialDatabits = serialDatabits;
        this.serialStopBits = serialStopBits;
        this.serialParity = serialParity;

        this.serialPort = new SerialPort(serialPortName);

        startSerial();
        configDevice(serialPort);
    }

    public void runCommand(Cmd command) {
        ViscaCommandHelper.sendCommand(serialPort, command);
        ViscaCommandHelper.readResponse(serialPort);
    }

    public void runCommandList(List<Cmd> commandList) {
        commandList.forEach(this::runCommand);
    }

    public void setDestDeviceAddress(byte destDeviceAddress) {
        Constants.DESTINATION_ADDRESS = destDeviceAddress;
    }

    private void startSerial() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }

    private void configDevice(SerialPort serialPort) {
        ViscaCommandHelper.sendCommand(serialPort, new AddressCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(2);

        ViscaCommandHelper.sendCommand(serialPort, new PanTiltHomeCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(2);
    }

    private void sleep(int timeSec) {
        try {
            Thread.sleep((long)(timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}
