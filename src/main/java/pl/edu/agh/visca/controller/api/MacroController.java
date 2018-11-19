package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.visca.service.ViscaParserService;
import pl.edu.agh.visca.service.ViscaService;
import pl.edu.agh.visca.service.macro.Macro;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@RestController
@RequestMapping("/controller/macro")
@AllArgsConstructor
@Slf4j
public class MacroController {

    private final ViscaService viscaService;
    private final ViscaParserService viscaParserService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addMacro(@RequestParam String macroName, @RequestParam String macroContent) {
        String response =  "OK";
        try {
            if (StringUtils.isBlank(macroName) || StringUtils.isBlank(macroContent))
                throw new IllegalArgumentException("Can not create macro for empty macroName or macroContent");

            val commandNames = viscaParserService.parseCommandInput(macroContent.trim());
            ViscaMacroHolder.addMacro(new Macro(macroName, commandNames));
            //response = viscaService.runCommandList(commandNames);
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public ResponseEntity runMacro(@RequestParam String macroName) {
        String response;
        try {
            val macro = ViscaMacroHolder.getMacro(macroName);
            response = viscaService.runCommandList(macro.getContent());
        } catch (Exception e) {
            response = e.getMessage();
        }

        log.debug("Response: " + response);

        return ResponseEntity.ok(response);
    }
}