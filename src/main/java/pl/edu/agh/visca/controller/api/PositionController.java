package pl.edu.agh.visca.controller.api;

import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.service.ViscaCommandHelper;
import pl.edu.agh.visca.service.exception.TimeoutException;

@RestController
@RequestMapping("/api/position")
@AllArgsConstructor
public class PositionController {
    private static final Logger logger = LoggerFactory.getLogger(PositionController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String direction, @RequestParam byte speed)
        throws SerialPortException, TimeoutException {

        //PositionDirection positionDirection = PositionDirection.valueOf(direction);
        String response = null;
        /*switch (positionDirection) {
            case UP: response = viscaCommandSender.sendPanTiltUp(speed); break;
            case DOWN: response = viscaCommandSender.sendPanTiltDown(speed); break;
            case LEFT: response = viscaCommandSender.sendPanTiltLeft(speed); break;
            case RIGHT: response = viscaCommandSender.sendPanTiltRight(speed); break;
        }*/

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
