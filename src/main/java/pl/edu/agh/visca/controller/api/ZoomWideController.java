package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.cmd.ZoomWideStdCmd;
import pl.edu.agh.visca.model.CommandName;
import pl.edu.agh.visca.model.Constants;
import pl.edu.agh.visca.service.ViscaService;

import static pl.edu.agh.visca.model.CommandName.ZOOM_WIDE;

@RestController
@RequestMapping("/controller/zoom-wide")
@AllArgsConstructor
@Slf4j
public class ZoomWideController {

    private final ViscaService viscaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity changeZoomWide(@RequestParam String command, @RequestParam ZoomWideStdCmd.CONSTANT_SPEED speed,
                                         @RequestParam(defaultValue = "1") String address) {

        String response;
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            val commandName = CommandName.valueOf(command);
            val realCommand = (ZoomWideStdCmd) commandName.getCommand();
            realCommand.setSpeed(speed);
            response = viscaService.runCommand(ZOOM_WIDE);
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
