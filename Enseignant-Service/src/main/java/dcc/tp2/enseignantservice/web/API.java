package dcc.tp2.enseignantservice.web;

import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.service.EnseignantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Enseignants")
public class API {

    private EnseignantService enseignantService;


    public API(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @PostMapping
    public ResponseEntity<Enseignant> add(@RequestBody Enseignant enseignant){
        Enseignant e = enseignantService.Create_Enseignant(enseignant);
        return ResponseEntity.ok(e);
    }

    @GetMapping
    public ResponseEntity<List<Enseignant>> GetALL(){
        List<Enseignant> enseignants = enseignantService.GetAll_Enseignant();
        return ResponseEntity.ok(enseignants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> Get_ByID(@PathVariable Long id){
        Enseignant e = enseignantService.Get_EnseignantByID(id);
        return ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> Update(@PathVariable Long id, @RequestBody Enseignant enseignant){
        Enseignant e = enseignantService.Update_Enseignant(enseignant,id);
        return ResponseEntity.ok(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        enseignantService.Delete_Enseignant(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/statistique/{id}")
    public ResponseEntity<Map<String,Long>> statistique(@PathVariable Long id){
        return  ResponseEntity.ok(enseignantService.statistique(id));
    }


    // utiliser dans login/ authentification de microservice-securité

    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String,String>> getByemail(@PathVariable String email){

        Enseignant enseignant = enseignantService.FindByEmail(email);
        if (enseignant!=null) {
            Map<String, String> infos_user = new HashMap<>();

            infos_user.put("email", enseignant.getEmail());
            infos_user.put("password", enseignant.getPassword());
            infos_user.put("scope", enseignant.getRole());
            return ResponseEntity.ok(infos_user);
        }
        return ResponseEntity.notFound().build();
    }





}
