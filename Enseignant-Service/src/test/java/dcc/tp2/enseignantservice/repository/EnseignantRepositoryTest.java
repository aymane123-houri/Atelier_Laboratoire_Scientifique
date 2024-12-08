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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @BeforeEach
    void setUp() {
        enseignantRepository.save(new Enseignant(null,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant"));
        enseignantRepository.save(new Enseignant(null,"nadir","hmimed","KB2244","nadir@mail.com","nadir123","informatique","Enseignant"));
    }


    @Test
    public void souldfindEnseignantByEmail(){
        String email = "oussama@mail.com";
        Enseignant enseignant = new Enseignant(null,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant");
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNotNull();
    }

    @Test
    public void souldNotfindEnseignantByEmail(){
        String email = "xy@z.com";
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
    }

}