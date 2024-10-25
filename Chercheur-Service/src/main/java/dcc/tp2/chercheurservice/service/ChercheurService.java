package dcc.tp2.chercheurservice.service;

import dcc.tp2.chercheurservice.client.EnseignantRestFeign;
import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.module.Enseignant;
import dcc.tp2.chercheurservice.repository.ChercheurRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChercheurService {

    private ChercheurRepository chercheurRepository;
    private EnseignantRestFeign enseignantRestFeign;

    public ChercheurService(ChercheurRepository chercheurRepository, EnseignantRestFeign enseignantRestFeign) {
        this.chercheurRepository = chercheurRepository;
        this.enseignantRestFeign = enseignantRestFeign;
    }


    public Chercheur Create_Chercheur( Chercheur chercheur){
        return chercheurRepository.save(chercheur);
    }


    public List<Chercheur> GetALL_Chercheur( ){
        List<Chercheur> chercheurList = chercheurRepository.findAll();

        for(Chercheur e: chercheurList){
            e.setEnseignant(enseignantRestFeign.Enseignant_ByID(e.getId_enseignant()));

        }

        return chercheurList;
    }

    public Chercheur Get_ChercheurById( Long id){
        Chercheur chercheur = chercheurRepository.findById(id).orElse(null);
        chercheur.setEnseignant(enseignantRestFeign.Enseignant_ByID(chercheur.getId_enseignant()));
        return  chercheur;
    }


    public Chercheur Get_ChercheurByEmail(String email){
        Chercheur chercheur = chercheurRepository.findChercheurByEmail(email);
        return  chercheur;
    }


    public Chercheur Update_Chercheur(Long id,Chercheur chercheur){
        return chercheurRepository.findById(id).map(c ->{
                c.setNom(chercheur.getNom());
                c.setPrenom(chercheur.getPrenom());
                c.setTel(chercheur.getTel());
                c.setEmail(chercheur.getEmail());
                c.setPassword(chercheur.getPassword());
                c.setRole(chercheur.getRole());
                c.setId_enseignant(chercheur.getId_enseignant());
                return chercheurRepository.save(c);
        }).orElseThrow(()-> new RuntimeException("Chercheur pas trouvée"));
    }

    public void Delete_Chercheur(Long id){
        chercheurRepository.deleteById(id);
    }

    public long getNombreDeChercheurParEnseignant(Long id_enseignant) {
        return chercheurRepository.nombre_chercheur_Enseignant(id_enseignant);
    }








}
