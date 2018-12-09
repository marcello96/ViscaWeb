package pl.edu.agh.visca.service.macro;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class ViscaMacroHolder {

    private Map<String, Macro> macroMap;

    public Macro addMacro(Macro macro) {
        return macroMap.put(macro.getName(), macro);
    }

    public Macro getMacro(String name) {
        Preconditions.checkArgument(MapUtils.isNotEmpty(macroMap) && macroMap.containsKey(name));
        return macroMap.get(name);
    }

    public List<Macro> getAllMacro() {
        return new ArrayList<>(macroMap.values());
    }

    public boolean deleteMacro(String name) {
        return Objects.nonNull(macroMap.remove(name));
    }
}
