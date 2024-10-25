package dcc.tp2.enseignantservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255)
    private String nom;
    @Column(length = 255)
    private String prenom;
    @Column(length = 255)
    private String cne;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String password;
    @Column(length = 255)
    private String thematique;
    @Column(length = 255)
    private String Role;



}
