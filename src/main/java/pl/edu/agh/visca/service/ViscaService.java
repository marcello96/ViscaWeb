package pl.edu.agh.visca.service;

import com.google.common.base.Preconditions;
import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.ViscaCommand;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.model.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Slf4j
public class ViscaService {
    private static final int TIME_SLEEPING = 2;

    private final String serialPortName;
    private final int serialBaudrate;
    private final int serialDatabits;
    private final int serialStopBits;
    private final int serialParity;

    private SerialPort serialPort;

    private ViscaResponseReader viscaResponseReader;
    private final ViscaResponseTranslator viscaResponseTranslator;


    @SneakyThrows
    public ViscaService(@Value("${serial.port}") String serialPortName,
                        @Value("${serial.baudrate}") int serialBaudrate,
                        @Value("${serial.databits}") int serialDatabits,
                        @Value("${serial.stopbits}") int serialStopBits,
                        @Value("${serial.parity}") int serialParity,
                        ViscaResponseReader viscaResponseReader,
                        ViscaResponseTranslator viscaResponseTranslator
    ) {
        this.serialPortName = serialPortName;
        this.serialBaudrate = serialBaudrate;
        this.serialDatabits = serialDatabits;
        this.serialStopBits = serialStopBits;
        this.serialParity = serialParity;
        this.viscaResponseReader = viscaResponseReader;
        this.viscaResponseTranslator = viscaResponseTranslator;
    }

    @SneakyThrows
    @PostConstruct
    public void setup() {
        //FIXME: changing when we have port connection
        /*log.debug("Serial opened!");
        serialPort = new SerialPort(serialPortName);

        startSerial();
        configDevice();*/
    }

    @PreDestroy
    @SneakyThrows
    public void teardown() {
        serialPort.closePort();
        log.debug("Serial closed!");
    }

    private void configDevice() {
        sendCommand(CommandName.ADDRESS.getCommand());
        readResponse();
    }


    @SneakyThrows
    public synchronized String runCommandList(List<Cmd> commandList) {
        return commandList.stream()
                .map(this::runCommand)
                .collect(Collectors.joining("\n"));
    }

    @SneakyThrows
    public synchronized String runCommand(Cmd commandName) {
        if(!commandName.isExecutable()) {
            commandName.prepareContent();
            return "DONE!";
        }

        sendCommand(commandName);
        String response = viscaResponseTranslator.translateResponse(readResponse());
        if (response.equals("ACK")) {
            String response2 = viscaResponseTranslator.translateResponse(readResponse());
            return  response + " " + response2;
        }
        return response;
    }

    private void startSerial() throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(serialBaudrate, serialDatabits, serialStopBits, serialParity);
    }


    @SneakyThrows
    private void sendCommand(Cmd command) {
        Preconditions.checkArgument(command.isExecutable());

        byte[] cmdData = command.prepareContent();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = command.isBroadcast() ? Constants.BROADCAST_ADDRESS : Constants.DESTINATION_ADDRESS;
        cmdData = vCmd.getViscaCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));

        serialPort.writeBytes(cmdData);
    }

    @SneakyThrows
    private String readResponse() {
        byte[] response = viscaResponseReader.readResponse(serialPort);
        return byteArrayToString(response);
    }

    private String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }

        return sb.toString();
    }
}
