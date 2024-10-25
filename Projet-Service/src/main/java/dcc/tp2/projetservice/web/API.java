package dcc.tp2.projetservice.web;

import dcc.tp2.projetservice.entities.Projet;
import dcc.tp2.projetservice.service.ProjetService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Projets")
public class API {

    private ProjetService projetService;

    public API(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    public ResponseEntity<Projet> add(@RequestBody Projet projet){
        try {
            Projet p = projetService.Create_Projet(projet);
            return ResponseEntity.ok(p);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Projet>> GetAll(){
        try {
            List<Projet> projetList = projetService.GetAll_Projet();
            return ResponseEntity.ok(projetList);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> GetByID(@PathVariable Long id){
        try {
            Projet projet = projetService.Get_ProjetById(id);
            return ResponseEntity.ok(projet);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Projet> update(@PathVariable Long id,@RequestBody Projet projet){
        try {
            Projet p = projetService.Update_Projet(id, projet);
            return ResponseEntity.ok(projet);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        try {
            projetService.Delete_Projet(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("Enseignant/{id}") // statistique sur projet.
    public ResponseEntity<Long> nb_projet_enseinant(@PathVariable Long id){
        try {
            long nombreDeProjetsParEnseignant = projetService.getNombreDeProjetsParEnseignant(id);
            return ResponseEntity.ok(nombreDeProjetsParEnseignant);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }



}
