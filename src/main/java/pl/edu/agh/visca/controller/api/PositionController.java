package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.cmd.*;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.model.Constants;
import pl.edu.agh.visca.service.ViscaService;

import static pl.edu.agh.visca.model.CommandName.*;

@RestController
@RequestMapping("/controller/position")
@AllArgsConstructor
@Slf4j
public class PositionController {
    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity changePosition(@RequestParam String command, @RequestParam ConstantPanSpeed panSpeed, @RequestParam ConstantTiltSpeed tiltSpeed,
                                         @RequestParam(defaultValue = "1") String address) {

        val commandName = CommandName.valueOf(command);
        String response;
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            switch (commandName) {
                case UP:
                    val commandUp = (PanTiltUpCmd) commandName.getCommand();
                    commandUp.setSpeed(tiltSpeed);
                    response = viscaService.runCommand(UP);
                    break;
                case DOWN:
                    val commandDown = (PanTiltDownCmd) commandName.getCommand();
                    commandDown.setSpeed(tiltSpeed);
                    response = viscaService.runCommand(DOWN);
                    break;
                case LEFT:
                    val commandLeft = (PanTiltLeftCmd) commandName.getCommand();
                    commandLeft.setSpeed(panSpeed);
                    response = viscaService.runCommand(LEFT);
                    break;
                case RIGHT:
                    val commandRight = (PanTiltRightCmd) commandName.getCommand();
                    commandRight.setSpeed(panSpeed);
                    response = viscaService.runCommand(RIGHT);
                    break;
                default:
                    throw new IllegalArgumentException("This is not position command: UP, DOWN, LEFT, RIGHT");

            }
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
