package pl.edu.agh.visca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.visca.service.macro.ViscaMacroHolder;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public ViscaMacroHolder getBeanViscaMacroHolder() {
        return new ViscaMacroHolder();
    }
}
