package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.model.Constants;
import pl.edu.agh.visca.service.ViscaService;

@RestController
@RequestMapping("/controller/home")
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity changePosition(@RequestParam String command,
                                         @RequestParam(defaultValue = "1") String address) {
        String response;
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            val commandName = CommandName.valueOf(command);
            if (commandName != CommandName.HOME)
                throw new IllegalArgumentException("This is not home command");
            response = viscaService.runCommand(commandName.getCommand());
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
