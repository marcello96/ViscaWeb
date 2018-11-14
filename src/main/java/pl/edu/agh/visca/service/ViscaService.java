package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.*;
import pl.edu.agh.visca.service.exception.TimeoutException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private SerialPort serialPort;

    @Autowired
    public ViscaService(@Value("${serial.port}") String serialPortName,
                        @Value("${serial.baudrate}") int serialBaudrate,
                        @Value("${serial.databits}") int serialDatabits,
                        @Value("${serial.stopbits}") int serialStopBits,
                        @Value("${serial.parity}") int serialParity,
                        ViscaResponseReader viscaResponseReader
                        ) {

        this.serialPortName = serialPortName;
        this.serialBaudrate = serialBaudrate;
        this.serialDatabits = serialDatabits;
        this.serialStopBits = serialStopBits;
        this.serialParity = serialParity;
        this.viscaResponseReader = viscaResponseReader;

        serialPort = new SerialPort(serialPortName);

        //FIXME: changing when we have port connection
        //startSerial();
        //configDevice();
    }

    public void runCommandList(List<Cmd> commandList) {
        commandList.forEach(this::runCommand);
    }

    public String runCommand(Cmd command) {
        sendCommand(command);
        return readResponse();
    }

    private void startSerial() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }

    private void configDevice() {
        sendCommand(new AddressCmd());
        readResponse();
        sleep(TIME_SLEEPING);

        sendCommand( new PanTiltHomeCmd());
        readResponse();
        sleep(TIME_SLEEPING);
    }

    private void sendCommand(Cmd command) {
        if (command instanceof WaitCmd) {
            sleep(((WaitCmd) command).getTime());
            return;
        }

        try {
            byte[] cmdData = command.createCommandData();
            ViscaCommand vCmd = new ViscaCommand();
            vCmd.commandData = cmdData;
            vCmd.sourceAdr = 0;
            vCmd.destinationAdr = command.getDestination();
            cmdData = vCmd.getViscaCommandData();
            System.out.println("@ " + byteArrayToString(cmdData));

            serialPort.writeBytes(cmdData);
        } catch (SerialPortException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private String readResponse() {
        try {
            byte[] response = viscaResponseReader.readResponse(serialPort);
            return byteArrayToString(response);
        } catch (TimeoutException | SerialPortException e) {
            return e.getMessage();
        }
    }

    private String byteArrayToString(byte[] bytes) {
        return Stream.of(bytes).map(b -> String.format("%02X", b))
                .collect(Collectors.joining(" "));
    }
}
