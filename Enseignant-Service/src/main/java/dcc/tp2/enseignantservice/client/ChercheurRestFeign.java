package dcc.tp2.enseignantservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CHERCHEUR-SERVICE")
public interface ChercheurRestFeign {

    @CircuitBreaker(name = "count-chercheur",fallbackMethod = "Chercheur_fallbackMethod")
    @GetMapping("/Chercheurs/Enseignant/{id}")
    Long nb_chercheur_Enseignant(@PathVariable Long id);

}
