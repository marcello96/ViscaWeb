package pl.edu.agh.visca.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageViewController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadMainPage(Model model) {
        model.addAttribute("listMacro", ViscaMacroHolder.getAllMacro());
        return "mainPage";
    }
}
