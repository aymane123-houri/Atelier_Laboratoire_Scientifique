package dcc.tp2.enseignantservice.ServiceTest;


import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import dcc.tp2.enseignantservice.service.EnseignantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnseignantServiceTest {

    @Mock
    private EnseignantRepository enseignantRepository;

    @Mock
    private ChercheurRestFeign chercheurRestFeign;

    @Mock
    private ProjetRestFeign projetRestFeign;

    @InjectMocks
    private EnseignantService enseignantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEnseignant() {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("John");
        enseignant.setEmail("john.doe@example.com");

        when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);

        Enseignant result = enseignantService.Create_Enseignant(enseignant);
        assertNotNull(result);
        assertEquals("John", result.getNom());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void testGetEnseignantById() {
        Enseignant enseignant = new Enseignant();
        enseignant.setId(1L);

        when(enseignantRepository.findById(1L)).thenReturn(Optional.of(enseignant));

        Enseignant result = enseignantService.Get_EnseignantByID(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testStatistique() {
        Long id = 1L;
        when(chercheurRestFeign.nb_chercheur_Enseignant(id)).thenReturn(5L);
        when(projetRestFeign.nb_Projet_Enseignant(id)).thenReturn(3L);

        var stats = enseignantService.statistique(id);
        assertEquals(5L, stats.get("nombre de chercheur"));
        assertEquals(3L, stats.get("nombre de projet"));
    }
}
