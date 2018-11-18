package pl.edu.agh.visca.service.macro;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViscaMacroHolder {

    private static Map<String, Macro> macroMap = new HashMap<>();

    public static void addMacro(Macro macro) {
        macroMap.put(macro.getName(), macro);
    }

    public static Macro getMacro(String name) {
        Preconditions.checkArgument(MapUtils.isNotEmpty(macroMap) && macroMap.containsKey(name));
        return macroMap.get(name);
    }

    public static List<String> getAllMacroName() {
        return new ArrayList<>(macroMap.keySet());
    }

    public static List<Macro> getAllMacro() {
        return new ArrayList<>(macroMap.values());
    }
}
