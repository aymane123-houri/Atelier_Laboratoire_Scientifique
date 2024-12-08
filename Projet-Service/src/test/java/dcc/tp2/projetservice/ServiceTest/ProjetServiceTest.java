package dcc.tp2.projetservice.ServiceTest;

import dcc.tp2.projetservice.client.ChercheurRestFeign;
import dcc.tp2.projetservice.entities.Projet;
import dcc.tp2.projetservice.module.Chercheur;
import dcc.tp2.projetservice.repository.ProjetRepository;
import dcc.tp2.projetservice.service.ProjetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjetServiceTest {

    @Mock
    private ChercheurRestFeign chercheurRestFeign;

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private ProjetService projetService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProjet() {
        Projet projet = new Projet();
        projet.setId(1L);
        projet.setTitre("Projet Test");

        when(projetRepository.save(projet)).thenReturn(projet);

        Projet result = projetService.Create_Projet(projet);

        assertNotNull(result);
        assertEquals("Projet Test", result.getTitre());
        verify(projetRepository, times(1)).save(projet);
    }


    @Test
    public void testUpdateProjet() {
        Projet projet = new Projet();
        projet.setId(1L);
        projet.setTitre("Projet Modifié");

        when(projetRepository.findById(1L)).thenReturn(Optional.of(new Projet()));
        when(projetRepository.save(any(Projet.class))).thenReturn(projet);

        Projet result = projetService.Update_Projet(1L, projet);

        assertNotNull(result);
        assertEquals("Projet Modifié", result.getTitre());
        verify(projetRepository, times(1)).findById(1L);
        verify(projetRepository, times(1)).save(any(Projet.class));
    }

    @Test
    public void testDeleteProjet() {
        doNothing().when(projetRepository).deleteById(1L);

        projetService.Delete_Projet(1L);

        verify(projetRepository, times(1)).deleteById(1L);
    }
}

