package dcc.tp2.projetservice.repository;

import dcc.tp2.projetservice.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet,Long> {

    @Query("SELECT COUNT(p) FROM Projet p WHERE p.id_enseignant = :enseignantId")
    long nombre_Projet_Enseignant(@Param("enseignantId") Long enseignantId);

}
