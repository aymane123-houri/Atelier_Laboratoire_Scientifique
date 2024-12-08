package dcc.tp2.projetservice.ControllerTest;

import dcc.tp2.projetservice.entities.Projet;
import dcc.tp2.projetservice.service.ProjetService;
import dcc.tp2.projetservice.web.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjetControllerTest {

    @Mock
    private ProjetService projetService;

    @InjectMocks
    private API projetController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProjet() {
        Projet projet = new Projet();
        projet.setId(1L);
        projet.setTitre("Projet Test");

        when(projetService.Create_Projet(projet)).thenReturn(projet);

        ResponseEntity<Projet> response = projetController.add(projet);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Projet Test", response.getBody().getTitre());
        verify(projetService, times(1)).Create_Projet(projet);
    }

    @Test
    public void testGetAllProjets() {
        Projet projet1 = new Projet();
        projet1.setId(1L);
        Projet projet2 = new Projet();
        projet2.setId(2L);

        when(projetService.GetAll_Projet()).thenReturn(Arrays.asList(projet1, projet2));

        ResponseEntity<List<Projet>> response = projetController.GetAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(projetService, times(1)).GetAll_Projet();
    }

    @Test
    public void testGetProjetById() {
        Projet projet = new Projet();
        projet.setId(1L);

        when(projetService.Get_ProjetById(1L)).thenReturn(projet);

        ResponseEntity<Projet> response = projetController.GetByID(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(projetService, times(1)).Get_ProjetById(1L);
    }

    @Test
    public void testUpdateProjet() {
        Projet projet = new Projet();
        projet.setId(1L);
        projet.setTitre("Projet Modifié");

        when(projetService.Update_Projet(1L, projet)).thenReturn(projet);

        ResponseEntity<Projet> response = projetController.update(1L, projet);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Projet Modifié", response.getBody().getTitre());
        verify(projetService, times(1)).Update_Projet(1L, projet);
    }

    @Test
    public void testDeleteProjet() {
        doNothing().when(projetService).Delete_Projet(1L);

        ResponseEntity<Void> response = projetController.Delete(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(projetService, times(1)).Delete_Projet(1L);
    }

    @Test
    public void testNbProjetEnseignant() {
        long nbProjets = 5L;
        when(projetService.getNombreDeProjetsParEnseignant(1L)).thenReturn(nbProjets);

        ResponseEntity<Long> response = projetController.nb_projet_enseinant(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(nbProjets, response.getBody());
        verify(projetService, times(1)).getNombreDeProjetsParEnseignant(1L);
    }
}
