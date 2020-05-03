package w.whateva.hh.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan
public class HundredHashesApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HundredHashesApplication.class, args);
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); //Registers all modules on classpath
        return mapper;
    }
}
