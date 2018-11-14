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
import pl.edu.agh.visca.cmd.ZoomTeleStdCmd;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.service.ViscaCommandHelper;

@RestController
@RequestMapping("/controller/zoom-tele")
@AllArgsConstructor
public class ZoomTeleController {
    private final Logger logger = LoggerFactory.getLogger(ZoomTeleController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changeZoomTele(@RequestParam String command, @RequestParam ZoomTeleStdCmd.CONSTANT_SPEED speed) {

        val commandName = CommandName.valueOf(command);
        val realCommand = (ZoomTeleStdCmd) commandName.getCommand();
        realCommand.setSpeed(speed);
        viscaCommandHelper.sendCommand(realCommand);
        String response = viscaCommandHelper.readResponse();

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
