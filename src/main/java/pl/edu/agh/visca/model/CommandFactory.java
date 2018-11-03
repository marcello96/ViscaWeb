package pl.edu.agh.visca.model;

import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.WaitCmd;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommandFactory {

    public Cmd createCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandName.WAIT.toString())) {
            int pos = inputCommand.indexOf("_");
            String time = inputCommand.substring(pos+1);
            WaitCmd cmd = (WaitCmd)CommandName.WAIT.getCommand();
            cmd.setTime(Integer.parseInt(time));
            return cmd;
        }
        return CommandName.valueOf(inputCommand).getCommand();
    }

    public List<Cmd> createCommandList(String[] inputcommands) {
        return Stream.of(inputcommands)
                        .map(this::createCommand)
                        .collect(Collectors.toList());

    }
}
