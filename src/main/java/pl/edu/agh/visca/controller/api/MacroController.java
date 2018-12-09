package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.visca.model.Constants;
import pl.edu.agh.visca.service.ViscaParserService;
import pl.edu.agh.visca.service.ViscaService;
import pl.edu.agh.visca.service.macro.Macro;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
@Slf4j
public class MacroController {
    private static final String OK = "ok";

    private final ViscaService viscaService;
    private final ViscaParserService viscaParserService;
    private final ViscaMacroHolder viscaMacroHolder;

    @RequestMapping(value = "/macro", method = RequestMethod.POST)
    public ResponseEntity addMacro(@RequestParam String macroName,
                                   @RequestParam String macroContent,
                                   @RequestParam(defaultValue = "1") String address) {
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            val commandNames = viscaParserService.parseCommandInput(macroContent.trim());
            viscaMacroHolder.addMacro(new Macro(macroName, commandNames));
        } catch (Exception e) {
            return new ResponseEntity<>("Can not parse the content", HttpStatus.BAD_REQUEST);
        }

        log.debug("Response: " + OK);

        return ResponseEntity.ok(OK);
    }

    @RequestMapping(value = "/macro", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMacro(@RequestParam String macroName,
                                              @RequestParam String macroContent,
                                              @RequestParam(defaultValue = "1") String address) {
        Constants.DESTINATION_ADDRESS = Byte.parseByte(address);
        try {
            val commandNames = viscaParserService.parseCommandInput(macroContent.trim());
            viscaMacroHolder.addMacro(new Macro(macroName, commandNames));
        } catch (Exception e) {
            return new ResponseEntity<>("Can not parse the content", HttpStatus.BAD_REQUEST);
        }

        log.debug("Response: " + OK);

        return ResponseEntity.ok(OK);
    }

    @RequestMapping(value = "/macro/{macroName}", method = RequestMethod.POST)
    public ResponseEntity runMacro(@PathVariable String macroName) {
        String response;
        try {
            val macro = viscaMacroHolder.getMacro(macroName);
            response = viscaService.runCommandList(macro.getContent());
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/macro/{macroName}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMacro(@PathVariable String macroName) {
        String response;
        try {
            response = Boolean.toString(viscaMacroHolder.deleteMacro(macroName));
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}