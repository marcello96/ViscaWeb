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
@RequestMapping("/controller/other")
@AllArgsConstructor
public class OtherController {
    private static final Logger logger = LoggerFactory.getLogger(OtherController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String command, @RequestParam byte speed)
        throws SerialPortException, TimeoutException {

        String response = null;
        /*switch (OtherCommands.valueOf(command)) {
            case HOME: response = viscaCommandHelper.sendPanTiltHome(); break;
        }*/

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
