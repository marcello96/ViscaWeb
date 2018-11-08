package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.ViscaCommand;
import pl.edu.agh.visca.cmd.WaitCmd;
import pl.edu.agh.visca.service.exception.TimeoutException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.edu.agh.visca.service.SleepUtility.sleep;

@Service
@AllArgsConstructor
public class ViscaCommandHelper {
    private ViscaResponseReader viscaResponseReader;

    public void sendCommand(SerialPort serialPort, Cmd command) {
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
            cmdData = vCmd.getCommandData();
            System.out.println("@ " + byteArrayToString(cmdData));

            serialPort.writeBytes(cmdData);
        } catch (SerialPortException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void readResponse(SerialPort serialPort) {
        try {
            byte[] response = viscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
        } catch (TimeoutException | SerialPortException e) {
            e.printStackTrace();
        }
    }

    private String byteArrayToString(byte[] bytes) {
        return Stream.of(bytes).map(b -> String.format("%02X", b))
                .collect(Collectors.joining(" "));
    }
}
