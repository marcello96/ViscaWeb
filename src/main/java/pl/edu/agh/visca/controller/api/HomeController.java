package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.service.ViscaService;

@RestController
@RequestMapping("/controller/home")
@AllArgsConstructor
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String command) {
        String response;
        try {
            val commandName = CommandName.valueOf(command);
            if (commandName != CommandName.PAN_TILT_HOME)
                throw new IllegalArgumentException("This is not home command");
            response = viscaService.runCommand(CommandName.PAN_TILT_HOME);
        } catch (Exception e) {
            response = e.getMessage();
        }

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
