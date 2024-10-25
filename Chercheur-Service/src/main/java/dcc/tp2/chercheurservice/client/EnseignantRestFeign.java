package dcc.tp2.chercheurservice.client;


import dcc.tp2.chercheurservice.module.Enseignant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENSEIGNANT-SERVICE")
public interface EnseignantRestFeign {

    @GetMapping("/Enseignants/{id}")
    Enseignant Enseignant_ByID(@PathVariable Long id);

}
