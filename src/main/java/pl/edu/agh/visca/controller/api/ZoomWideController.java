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
import pl.edu.agh.visca.cmd.ZoomWideStdCmd;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.service.ViscaCommandHelper;

@RestController
@RequestMapping("/controller/zoom-wide")
@AllArgsConstructor
public class ZoomWideController {
    private final Logger logger = LoggerFactory.getLogger(ZoomWideController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changeZoomWide(@RequestParam String command, @RequestParam ZoomWideStdCmd.CONSTANT_SPEED speed) {

        val commandName = CommandName.valueOf(command);
        val realCommand = (ZoomWideStdCmd) commandName.getCommand();
        realCommand.setSpeed(speed);
        viscaCommandHelper.sendCommand(commandName.getCommand());
        String response = viscaCommandHelper.readResponse();

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
