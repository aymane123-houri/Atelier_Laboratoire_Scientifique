package dcc.tp2.chercheurservice.repository;

import dcc.tp2.chercheurservice.entities.Chercheur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChercheurRepository extends JpaRepository<Chercheur,Long> {

    Chercheur findChercheurByEmail(String email); // utiliser en login

    @Query("SELECT COUNT(c) FROM Chercheur c WHERE c.id_enseignant = :enseignantId")
    long nombre_chercheur_Enseignant(@Param("enseignantId") Long enseignantId);

}
