package pl.edu.agh.visca.controller.api;

import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/controller/macro/add")
@AllArgsConstructor
public class MacroController {
    private static final Logger logger = LoggerFactory.getLogger(MacroController.class);

    private final ViscaService viscaService;
    private final ViscaParserService viscaParserService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String macroName, @RequestParam String macroContent) {
        String response;
        try {
            if (StringUtils.isBlank(macroName) || StringUtils.isBlank(macroContent))
                throw new IllegalArgumentException("Can not create macro for empty macroName or macroContent");

            val commandNames = viscaParserService.parseCommandInput(macroContent.trim());
            ViscaMacroHolder.addMacro(new Macro(macroName, commandNames));
            response = viscaService.runCommandList(commandNames);
        } catch (Exception e) {
            response = e.getMessage();
        }

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}