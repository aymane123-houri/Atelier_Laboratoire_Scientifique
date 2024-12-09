package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository enseignantRepository;


    //la premiere methode qui va lance je vais ajouter des belement dans la base de donner pour les autre methodes va fonctionne
    @BeforeEach
    void setUp(){
        enseignantRepository.save(new Enseignant(null,"test","test","LA12346","houri@gmail.com","1234","informatique","Enseignant"));
        enseignantRepository.save(new Enseignant(null,"aymane","houri","LA1234","aymane@gmail.com","1234","informatique","Enseignant"));
    }
    @Test
    void findEnseignantByEmail(){
        String email="houri@gmail.com";
        Enseignant enseignant  = new Enseignant(null,"test","test","LA12346","houri@gmail.com","1234","informatique","Enseignant");

        Enseignant result=enseignantRepository.findEnseignantByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(enseignant);
    }

}