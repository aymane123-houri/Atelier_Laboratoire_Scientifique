package dcc.tp2.enseignantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnseignantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnseignantServiceApplication.class, args);
    }

}
