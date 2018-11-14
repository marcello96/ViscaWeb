package pl.edu.agh.visca.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.visca.service.ViscaService;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageViewController {

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) {
        Integer maxSpeed = 10;
        model.addAttribute("maxSpeed", maxSpeed);
        return "mainPage";
    }
}
