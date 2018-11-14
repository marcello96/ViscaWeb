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
import pl.edu.agh.visca.cmd.*;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.service.ViscaCommandHelper;

@RestController
@RequestMapping("/controller/position")
@AllArgsConstructor
public class PositionController {
    private static final Logger logger = LoggerFactory.getLogger(PositionController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String command, @RequestParam ConstantPanSpeed panSpeed, @RequestParam ConstantTiltSpeed tiltSpeed) {

        val commandName = CommandName.valueOf(command);
        switch (commandName) {
            case PAN_TILT_UP:
                val commandUp = (PanTiltUpCmd) commandName.getCommand();
                commandUp.setSpeed(panSpeed, tiltSpeed);
                viscaCommandHelper.sendCommand(commandUp);
                break;
            case PAN_TILT_DOWN:
                val commandDown = (PanTiltUpCmd) commandName.getCommand();
                commandDown.setSpeed(panSpeed, tiltSpeed);
                viscaCommandHelper.sendCommand(commandDown);
                break;
            case PAN_TILT_LEFT:
                val commandLeft = (PanTiltLeftCmd) commandName.getCommand();
                commandLeft.setSpeed(panSpeed, tiltSpeed);
                viscaCommandHelper.sendCommand(commandLeft);
                break;
            case PAN_TILT_RIGHT:
                val commandRight = (PanTiltRightCmd) commandName.getCommand();
                commandRight.setSpeed(panSpeed, tiltSpeed);
                viscaCommandHelper.sendCommand(commandRight);
        }
        String response = viscaCommandHelper.readResponse();

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
