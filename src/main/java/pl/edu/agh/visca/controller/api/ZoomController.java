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
@RequestMapping("/controller/zoom")
@AllArgsConstructor
public class ZoomController {
    private final Logger logger = LoggerFactory.getLogger(ZoomController.class);

    private final ViscaCommandHelper viscaCommandHelper;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changeZoom(@RequestParam String zoom, @RequestParam byte speed) throws SerialPortException, TimeoutException {

        //Zoom zoom1 = Zoom.valueOf(zoom);
        String response = null;
        /*switch (zoom1) {
            case WIDE: response = viscaCommandSender.sendZoomWideStd(speed); break;
            case TELE: response = viscaCommandSender.sendZoomTeleStd(speed); break;
        }*/
        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
