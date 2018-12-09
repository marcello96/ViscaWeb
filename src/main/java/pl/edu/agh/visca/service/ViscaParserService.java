package pl.edu.agh.visca.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.model.CommandFactory;

import java.util.List;

@Service
@AllArgsConstructor
public class ViscaParserService {
    private final CommandFactory commandFactory;

    @SneakyThrows
    public List<Cmd> parseCommandInput(String input) {
        return commandFactory.createCommandList(input.split(" "));
    }
}
