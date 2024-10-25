package dcc.tp2.security_microservice;

import dcc.tp2.security_microservice.configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)

public class SecurityMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityMicroserviceApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
