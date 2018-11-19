package pl.edu.agh.visca.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ViscaResponseTranslator {
    private static final Map<String, String> MAP = ImmutableMap.<String, String>builder()
                                                        .put("41 FF", "ACK")
                                                        .put("51 FF", "OK")

                                                        .put("60 01 FF", "Message length error (>14 bytes)")
                                                        .put("60 02 FF", "Syntax error")
                                                        .put("60 03 FF", "Command buffer full")
                                                        .put("60 04 FF", "Command cancelled")
                                                        .put("60 05 FF", "No sockets")
                                                        .put("60 41 FF", "Command not executable")
                                                        .build();


    public String translateResponse(String response) {
        String responseBody = response.substring(response.indexOf(" ")).trim();
        return MAP.getOrDefault(responseBody, response);
    }
}
