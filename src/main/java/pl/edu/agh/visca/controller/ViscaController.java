package pl.edu.agh.visca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.service.ViscaParserService;
import pl.edu.agh.visca.service.ViscaService;


@RestController("/visca")
public class ViscaController {

    private final ViscaParserService viscaParserService;
    private final ViscaService viscaService;

    public ViscaController(ViscaParserService viscaParserService, ViscaService viscaService) {
        this.viscaParserService = viscaParserService;
        this.viscaService = viscaService;
    }

    @GetMapping("/run")
    public ResponseEntity<String> runCommand(@RequestParam("cmd") String cmd,
                                             @RequestParam(value = "device", defaultValue = "1") byte deviceAddress) {
        viscaService.setDestDeviceAddress(deviceAddress);
        viscaService.runCommandList(viscaParserService.parseCommandInput(cmd));

        return ResponseEntity.ok("Run succeed!");
    }
}
