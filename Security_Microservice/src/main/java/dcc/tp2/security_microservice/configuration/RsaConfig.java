package dcc.tp2.security_microservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties("rsa")

public record RsaConfig(RSAPublicKey publicKey , RSAPrivateKey privateKey) {
}
