package pl.edu.agh.visca.service.macro;

import lombok.Value;
import pl.edu.agh.visca.model.CommandName;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Macro implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<CommandName> content;

    public String getContentAsString() {
        return content.stream()
                .map(CommandName::name)
                .collect(Collectors.joining(" "));
    }
}