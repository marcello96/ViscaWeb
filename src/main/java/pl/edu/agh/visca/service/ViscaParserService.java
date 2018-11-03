package pl.edu.agh.visca.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.model.CommandFactory;

import java.util.List;

@Service
public class ViscaParserService {

    private CommandFactory commandFactory;

    public ViscaParserService(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }


    public List<Cmd> parseCommandInput(String input) {
        return commandFactory.createCommandList(parseInput(input));
    }


    private String[] parseInput(String userInput) {
        if (userInput.startsWith("macro:")) {
            String commands = userInput.substring(6);

            return commands.split(";");
        }
        return new String[] {userInput};
    }
}
