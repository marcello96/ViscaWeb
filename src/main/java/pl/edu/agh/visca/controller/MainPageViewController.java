package pl.edu.agh.visca.controller;

import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.visca.service.ViscaCommandHelper;
import pl.edu.agh.visca.service.exception.TimeoutException;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainPageViewController {

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) throws TimeoutException, SerialPortException {
        Integer maxSpeed = 10;
        model.addAttribute("maxSpeed", maxSpeed);
        return "mainPage";
    }
}
