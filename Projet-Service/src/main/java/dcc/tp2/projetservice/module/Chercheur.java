package dcc.tp2.projetservice.module;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Chercheur {

    private  Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private Long id_enseignant;

    // juste pour récuperation de l'enseignant encadrée.
    private Enseignant enseignant;
}
