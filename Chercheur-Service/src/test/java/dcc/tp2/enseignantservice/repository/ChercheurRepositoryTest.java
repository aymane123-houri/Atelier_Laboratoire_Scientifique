package dcc.tp2.enseignantservice.repository;


import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.repository.ChercheurRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ChercheurRepositoryTest {

    private ChercheurRepository chercheurRepository;

    public ChercheurRepositoryTest(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
    }

    @BeforeEach
    void setUp() {
        chercheurRepository.save(new Chercheur(null,"oussama","alaoui","la1223","oussama@mail.com","oussama123","Chercheur" ,null,""));
        chercheurRepository.save(new Chercheur(null,"nadir","hmimed","KB2244","nadir@mail.com","nadir123","informatique","Enseignant"));
    }


    @Test
    public void souldfindChercheurByEmail(){
        String email = "oussama@mail.com";
        Chercheur chercheur = new Chercheur(null,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant");
        Chercheur result = chercheurRepository.findChercheurByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNotNull();
    }

    @Test
    public void souldNotfindChercheurByEmail(){
        String email = "xy@z.com";
        Chercheur result = chercheurRepository.findChercheurByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
    }

}