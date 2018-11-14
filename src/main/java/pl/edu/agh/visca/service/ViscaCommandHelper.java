package pl.edu.agh.visca.service;

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
    private final ViscaResponseReader viscaResponseReader;
    private final ViscaService viscaService;

    public void sendCommand(Cmd command) {
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

            viscaService.getSerialPort().writeBytes(cmdData);
        } catch (SerialPortException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public String readResponse() {
        try {
            byte[] response = viscaResponseReader.readResponse(viscaService.getSerialPort());
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
