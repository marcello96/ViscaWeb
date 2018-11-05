package pl.edu.agh.visca.service;

import org.springframework.stereotype.Repository;
import pl.edu.agh.visca.cmd.Cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ViscaMacroHolder {

    private final Map<String, List<Cmd>> macroMap;

    public ViscaMacroHolder() {
        macroMap = new HashMap<>();
    }

    public void addMacro(String name, List<Cmd> commands) {
        macroMap.put(name, commands);
    }

    public List<Cmd> getMacro(String name) {
        return macroMap.get(name);
    }
}
