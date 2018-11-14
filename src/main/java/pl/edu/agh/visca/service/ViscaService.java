package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.AddressCmd;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.PanTiltHomeCmd;

import java.util.List;

import static pl.edu.agh.visca.service.SleepUtility.sleep;

@Service
@Getter
public class ViscaService {
    private static final int TIME_SLEEPING = 2;

    private final String serialPortName;
    private final int serialBaudrate;
    private final int serialDatabits;
    private final int serialStopBits;
    private final int serialParity;
    private ViscaResponseReader viscaResponseReader;
    private ViscaCommandHelper viscaCommandHelper;

    private SerialPort serialPort;

    @Autowired
    public ViscaService(@Value("${serial.port}") String serialPortName,
                        @Value("${serial.baudrate}") int serialBaudrate,
                        @Value("${serial.databits}") int serialDatabits,
                        @Value("${serial.stopbits}") int serialStopBits,
                        @Value("${serial.parity}") int serialParity,
                        ViscaResponseReader viscaResponseReader,
                        ViscaCommandHelper viscaCommandHelper) {

        this.serialPortName = serialPortName;
        this.serialBaudrate = serialBaudrate;
        this.serialDatabits = serialDatabits;
        this.serialStopBits = serialStopBits;
        this.serialParity = serialParity;
        this.viscaResponseReader = viscaResponseReader;
        this.viscaCommandHelper = viscaCommandHelper;

        serialPort = new SerialPort(serialPortName);

        //FIXME: changing when we have port connection
        //startSerial();
        //configDevice(serialPort);
    }

    public void runCommand(Cmd command) {
        viscaCommandHelper.sendCommand(serialPort, command);
        viscaCommandHelper.readResponse(serialPort);
    }

    public void runCommandList(List<Cmd> commandList) {
        commandList.forEach(this::runCommand);
    }

    private void startSerial() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }

    private void configDevice(SerialPort serialPort) {
        viscaCommandHelper.sendCommand(serialPort, new AddressCmd());
        viscaCommandHelper.readResponse(serialPort);
        sleep(TIME_SLEEPING);

        viscaCommandHelper.sendCommand(serialPort, new PanTiltHomeCmd());
        viscaCommandHelper.readResponse(serialPort);
        sleep(TIME_SLEEPING);
    }
}
