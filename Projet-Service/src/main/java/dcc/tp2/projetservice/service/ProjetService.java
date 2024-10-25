package dcc.tp2.projetservice.service;

import dcc.tp2.projetservice.client.ChercheurRestFeign;
import dcc.tp2.projetservice.entities.Projet;
import dcc.tp2.projetservice.module.Chercheur;
import dcc.tp2.projetservice.repository.ProjetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjetService {
    private ChercheurRestFeign chercheurRestFeign;
    private ProjetRepository projetRepository;

    public ProjetService(ChercheurRestFeign chercheurRestFeign, ProjetRepository projetRepository) {
        this.chercheurRestFeign = chercheurRestFeign;
        this.projetRepository = projetRepository;
    }

    public Projet Create_Projet(Projet projet){
        return projetRepository.save(projet);
    }


    public List<Projet> GetAll_Projet(){

        List<Projet> projetList = projetRepository.findAll();

        for (Projet p :projetList){
            p.setChercheur(chercheurRestFeign.GetChercheurByID(p.getId_chercheur()));
        }
        return projetList;
    }

    public Projet Get_ProjetById(Long id){
        Projet projet = projetRepository.findById(id).orElse(null);
        projet.setChercheur(chercheurRestFeign.GetChercheurByID(projet.getId_chercheur()));
        return projet;
    }

    public Projet Update_Projet(Long id , Projet projet){
        return projetRepository.findById(id).map(p ->{
            p.setTitre(projet.getTitre());
            p.setDescription(projet.getDescription());
            p.setStatus(projet.getStatus());
            p.setId_chercheur(projet.getId_chercheur());
            p.setId_enseignant(projet.getId_enseignant());
            return projetRepository.save(p);
        }).orElseThrow(()->new RuntimeException("Projet non trouvé"));
    }

    public void Delete_Projet(Long id){
        projetRepository.deleteById(id);
    }


    public long getNombreDeProjetsParEnseignant(Long id_enseignant) {
        return projetRepository.nombre_Projet_Enseignant(id_enseignant);
    }








}
