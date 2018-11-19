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
import pl.edu.agh.visca.model.Constants;
import pl.edu.agh.visca.service.ViscaService;

import static pl.edu.agh.visca.model.CommandName.ZOOM_TELE;

@RestController
@RequestMapping("/controller/zoom-tele")
@AllArgsConstructor
public class ZoomTeleController {
    private final Logger logger = LoggerFactory.getLogger(ZoomTeleController.class);

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity changeZoomTele(@RequestParam String command, @RequestParam ZoomTeleStdCmd.CONSTANT_SPEED speed,
                                         @RequestParam(defaultValue = "1") String address) {

        String response;
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            val commandName = CommandName.valueOf(command);
            val realCommand = (ZoomTeleStdCmd) commandName.getCommand();
            realCommand.setSpeed(speed);
            response = viscaService.runCommand(ZOOM_TELE);
        } catch (Exception e) {
            response = e.getMessage();
        }

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
