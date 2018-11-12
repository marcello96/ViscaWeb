package pl.edu.agh.visca.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.service.ViscaParserService;
import pl.edu.agh.visca.service.ViscaService;


@RestController
@RequestMapping("/visca_web")
@AllArgsConstructor
public class ViscaController {

    private final ViscaParserService viscaParserService;
    private final ViscaService viscaService;

    @GetMapping("/run")
    public ResponseEntity<String> runCommand(@RequestParam("cmd") String cmd,
                                             @RequestParam(value = "device", defaultValue = "1") byte deviceAddress) {
        //viscaService.setDestDeviceAddress(deviceAddress);
        //viscaService.runCommandList(viscaParserService.parseCommandInput(cmd));

        return ResponseEntity.ok("Run succeed!");
    }
}
