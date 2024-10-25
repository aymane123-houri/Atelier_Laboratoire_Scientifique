package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {

    Enseignant findEnseignantByEmail(String email);

}
