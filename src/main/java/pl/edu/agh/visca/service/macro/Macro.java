package pl.edu.agh.visca.service.macro;

import lombok.Value;
import pl.edu.agh.visca.model.CommandName;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class Macro {
    private String name;
    private List<CommandName> content;

    public String getContentAsString() {
        return content.stream()
                .map(CommandName::name)
                .collect(Collectors.joining(" "));
    }
}