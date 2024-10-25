package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnseignantService {

    private EnseignantRepository enseignantRepository;
    private ChercheurRestFeign chercheurRestFeign;
    private ProjetRestFeign projetRestFeign;

    public EnseignantService(EnseignantRepository enseignantRepository, ChercheurRestFeign chercheurRestFeign, ProjetRestFeign projetRestFeign) {

        this.enseignantRepository = enseignantRepository;
        this.chercheurRestFeign = chercheurRestFeign;
        this.projetRestFeign = projetRestFeign;
    }

    public Enseignant Create_Enseignant( Enseignant e){
        return enseignantRepository.save(e);
    }

    public List<Enseignant> GetAll_Enseignant(){
        return enseignantRepository.findAll();
    }

    public Enseignant Get_EnseignantByID(Long id){
        return enseignantRepository.findById(id).orElse(null);
    }

    public Enseignant FindByEmail(String email){
        return enseignantRepository.findEnseignantByEmail(email);
    }

    public Enseignant Update_Enseignant(Enseignant enseignant, Long id){

        return enseignantRepository.findById(id).map(e -> {
            e.setNom(enseignant.getNom());
            e.setPrenom(enseignant.getPrenom());
            e.setCne(enseignant.getCne());
            e.setEmail(enseignant.getEmail());
            e.setPassword(enseignant.getPassword());
            e.setThematique(enseignant.getThematique());
            e.setRole(enseignant.getRole());
            return enseignantRepository.save(e);
        }).orElseThrow(()-> new RuntimeException("enseignant non trouvé"));

    }

    public void Delete_Enseignant(Long id){
        enseignantRepository.deleteById(id);
    }



    public Map<String,Long> statistique(Long id){

        Long nb_chercheur = chercheurRestFeign.nb_chercheur_Enseignant(id);
        Long nb_projet = projetRestFeign.nb_Projet_Enseignant(id);

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",nb_projet);
        Statistiques.put("nombre de chercheur",nb_chercheur);
        return  Statistiques;
    }














}
