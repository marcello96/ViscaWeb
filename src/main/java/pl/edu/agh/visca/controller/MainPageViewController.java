package pl.edu.agh.visca.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.visca.controller.api.MacroController;
import pl.edu.agh.visca.service.ViscaService;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageViewController {
    private static final Logger logger = LoggerFactory.getLogger(MacroController.class);

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) {
        model.addAttribute("listMacro", ViscaMacroHolder.getAllMacro());
        return "mainPage";
    }
}
