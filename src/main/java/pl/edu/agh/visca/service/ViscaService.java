package pl.edu.agh.visca.service;

import com.google.common.base.Preconditions;
import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.ViscaCommand;
import pl.edu.agh.visca.cmd.WaitCmd;
import pl.edu.agh.visca.model.CommandName;

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

    @SneakyThrows
    public String runCommandList(List<CommandName> commandList) {
        return commandList.stream()
                .map(this::runCommand)
                .collect(Collectors.joining("\n"));
    }

    @SneakyThrows
    public String runCommand(CommandName commandName) {
        switch (commandName) {
            case PAN_TILT_HOME:
                sendHomeCommand();
                break;
            case WAIT:
                sendWaitCommand();
                break;
            default:
                sendCommand(commandName);
        }

        return readResponse();
    }

    private void startSerial() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }

    private void configDevice() {
        sendCommand(CommandName.ADDRESS);
        readResponse();
        sleep(TIME_SLEEPING);

        sendHomeCommand();
    }

    @SneakyThrows
    private void sendCommand(CommandName commandName) {
        Preconditions.checkArgument(commandName != CommandName.WAIT);
        Preconditions.checkArgument(commandName != CommandName.PAN_TILT_HOME);

        val command = commandName.getCommand();
        byte[] cmdData = command.createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = command.getDestination();
        cmdData = vCmd.getViscaCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));

        serialPort.writeBytes(cmdData);
    }

    @SneakyThrows
    private void sendHomeCommand() {
        sendCommand(CommandName.PAN_TILT_HOME);
        sleep(TIME_SLEEPING);
    }

    @SneakyThrows
    private void sendWaitCommand() {
        sleep(((WaitCmd) CommandName.WAIT.getCommand()).getTime());
    }

    @SneakyThrows
    private String readResponse() {
        byte[] response = viscaResponseReader.readResponse(serialPort);
        return byteArrayToString(response);
    }

    private String byteArrayToString(byte[] bytes) {
        return Stream.of(bytes).map(b -> String.format("%02X", b))
                .collect(Collectors.joining(" "));
    }
}
