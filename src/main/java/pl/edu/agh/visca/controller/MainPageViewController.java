package pl.edu.agh.visca.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageViewController {

    private final ViscaMacroHolder viscaMacroHolder;

    @RequestMapping(method = RequestMethod.GET)
    public String loadMainPage() {
        return "mainPage";
    }

    @RequestMapping(value = "/macroes", method = RequestMethod.GET)
    public String loadAllMacroes(Model model) {
        model.addAttribute("listMacro", viscaMacroHolder.getAllMacro());
        return "macro";
    }

    @RequestMapping(value = "/macro/new", method = RequestMethod.GET)
    public String loadNewMacroPage() {
        return "newMacro";
    }

    @RequestMapping(value = "/macro/edit/{name}", method = RequestMethod.GET)
    public String loadNewMacroPage(@PathVariable String name, Model model) {
        model.addAttribute("macro", viscaMacroHolder.getMacro(name));
        return "editMacro";
    }
}
