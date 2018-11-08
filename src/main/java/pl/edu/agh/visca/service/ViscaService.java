package pl.edu.agh.visca.service;

import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ViscaService {
    private static final int TIME_SLEEPING = 2;

    private final String serialPortName;
    private final int serialBaudrate;
    private final int serialDatabits;
    private final int serialStopBits;
    private final int serialParity;

    private ViscaResponseReader viscaResponseReader;

    public ViscaService(@Value("${serial.port}") String serialPortName,
                        @Value("${serial.baudrate}") int serialBaudrate,
                        @Value("${serial.databits}") int serialDatabits,
                        @Value("${serial.stopbits}") int serialStopBits,
                        @Value("${serial.parity}") int serialParity,
                        ViscaResponseReader viscaResponseReader) throws SerialPortException {

        this.serialPortName = serialPortName;
        this.serialBaudrate = serialBaudrate;
        this.serialDatabits = serialDatabits;
        this.serialStopBits = serialStopBits;
        this.serialParity = serialParity;
        this.viscaResponseReader = viscaResponseReader;

        /*val serialPort = new SerialPort(serialPortName);

        startSerial(serialPort);
        configDevice(serialPort);*/
    }

    /*public void runCommand(Cmd command) {
        ViscaCommandHelper.sendCommand(serialPort, command);
        ViscaCommandHelper.readResponse(serialPort);
    }

    public void runCommandList(List<Cmd> commandList) {
        commandList.forEach(this::runCommand);
    }

    private void startSerial(SerialPort serialPort) throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }

    private void configDevice(SerialPort serialPort) {
        ViscaCommandHelper.sendCommand(serialPort, new AddressCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(TIME_SLEEPING);

        ViscaCommandHelper.sendCommand(serialPort, new PanTiltHomeCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(TIME_SLEEPING);
    }*/
}
