package pl.edu.agh.visca.service.macro;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViscaMacroHolder {

    private Map<String, Macro> macroMap;

    public ViscaMacroHolder() {
        macroMap = new HashMap<>();
    }

    public void addMacro(Macro macro) {
        macroMap.put(macro.getName(), macro);
    }

    public Macro getMacro(String name) {
        Preconditions.checkArgument(MapUtils.isNotEmpty(macroMap) && macroMap.containsKey(name));
        return macroMap.get(name);
    }

    public List<String> getAllMacroName() {
        return new ArrayList<>(macroMap.keySet());
    }

    public List<Macro> getAllMacro() {
        return new ArrayList<>(macroMap.values());
    }
}
