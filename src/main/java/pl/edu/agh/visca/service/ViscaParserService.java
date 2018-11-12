package pl.edu.agh.visca.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.model.CommandFactory;

import java.util.List;

@Service
@AllArgsConstructor
public class ViscaParserService {

    //FIXME: what is this????
    private static final String MACRO_DEF = "macrodef:";
    private static final String MACRO_RUN = "macrorun:";

    private final CommandFactory commandFactory;
    private final ViscaMacroHolder viscaMacroHolder;


    public List<Cmd> parseCommandInput(String input) {
        if (input.startsWith(MACRO_DEF)) {
            String commands = input.substring(MACRO_DEF.length());

            return createMacro(commands);
        } else if (input.startsWith(MACRO_RUN)) {
            String name = input.substring(MACRO_RUN.length());

            return viscaMacroHolder.getMacro(name);
        }

        return commandFactory.createCommandList(new String[] {input});
    }

    private List<Cmd> createMacro(String input) {
        int endOfNamePos = input.indexOf("=");
        String name = input.substring(0, endOfNamePos);
        String commands = input.substring(endOfNamePos + 1);
        List<Cmd> cmdList = commandFactory.createCommandList(commands.split(";"));

        viscaMacroHolder.addMacro(name, cmdList);
        return cmdList;
    }
}
