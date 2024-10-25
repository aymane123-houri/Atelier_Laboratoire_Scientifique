package dcc.tp2.chercheurservice.web;

import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.service.ChercheurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Chercheurs")
public class API {

    private ChercheurService chercheurService;

    public API(ChercheurService chercheurService) {
        this.chercheurService = chercheurService;
    }

    @PostMapping
    public ResponseEntity<Chercheur> add( @RequestBody Chercheur chercheur){
        Chercheur c = chercheurService.Create_Chercheur(chercheur);
        return ResponseEntity.ok(c);
    }


    @GetMapping
    public ResponseEntity<List<Chercheur>> GetAll(){
        List<Chercheur> chercheurList = chercheurService.GetALL_Chercheur();
        return ResponseEntity.ok(chercheurList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chercheur> GetById(@PathVariable Long id){
        Chercheur c = chercheurService.Get_ChercheurById(id);
        return ResponseEntity.ok(c);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Chercheur> Update( @PathVariable Long id, @RequestBody Chercheur chercheur){
        Chercheur c = chercheurService.Update_Chercheur(id,chercheur);
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id){
        chercheurService.Delete_Chercheur(id);
    }


    @GetMapping("Enseignant/{id}") // utiliser par enseignant pour statis.
    public ResponseEntity<Long> nb_chercheur_enseignant(@PathVariable Long id) {
        try {
            long nombreDeChercheurParEnseignant = chercheurService.getNombreDeChercheurParEnseignant(id);
            return ResponseEntity.ok(nombreDeChercheurParEnseignant);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
        @GetMapping("/email/{email}") // utiliser login
        public ResponseEntity<Map<String,String>> getByemail(@PathVariable String email){

            Chercheur chercheur = chercheurService.Get_ChercheurByEmail(email);
            if (chercheur!=null) {
                Map<String, String> infos_user = new HashMap<>();

                infos_user.put("email", chercheur.getEmail());
                infos_user.put("password", chercheur.getPassword());
                infos_user.put("scope", chercheur.getRole());
                return ResponseEntity.ok(infos_user);
            }
            return ResponseEntity.notFound().build();
        }

    }
