package pl.edu.agh.visca.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import pl.edu.agh.visca.cmd.Cmd;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class ViscaMacroHolder {

    @NonNull private final Multimap<String, Cmd> macroMap = ArrayListMultimap.create();

    public void addMacro(String name, List<Cmd> commands) {
        macroMap.putAll(name, commands);
    }

    public List<Cmd> getMacro(String name) {
        Preconditions.checkArgument(!macroMap.isEmpty() && macroMap.containsKey(name));
        return new ArrayList<>(macroMap.get(name));
    }
}
