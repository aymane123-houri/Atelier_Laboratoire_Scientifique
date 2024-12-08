package dcc.tp2.chercheurservice.ChercheurServiceTest;

import dcc.tp2.chercheurservice.client.EnseignantRestFeign;
import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.repository.ChercheurRepository;
import dcc.tp2.chercheurservice.service.ChercheurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChercheurServiceTest {

    @Mock
    private ChercheurRepository chercheurRepository;

    @Mock
    private EnseignantRestFeign enseignantRestFeign;

    @InjectMocks
    private ChercheurService chercheurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChercheur() {
        Chercheur chercheur = new Chercheur();
        chercheur.setNom("TestNom");
        when(chercheurRepository.save(chercheur)).thenReturn(chercheur);

        Chercheur result = chercheurService.Create_Chercheur(chercheur);

        assertEquals("TestNom", result.getNom());
        verify(chercheurRepository, times(1)).save(chercheur);
    }

    @Test
    void testGetAllChercheurs() {
        Chercheur chercheur1 = new Chercheur();
        chercheur1.setId_enseignant(1L);
        Chercheur chercheur2 = new Chercheur();
        chercheur2.setId_enseignant(2L);

        when(chercheurRepository.findAll()).thenReturn(Arrays.asList(chercheur1, chercheur2));
        when(enseignantRestFeign.Enseignant_ByID(1L)).thenReturn(null);
        when(enseignantRestFeign.Enseignant_ByID(2L)).thenReturn(null);

        var result = chercheurService.GetALL_Chercheur();

        assertEquals(2, result.size());
        verify(chercheurRepository, times(1)).findAll();
        verify(enseignantRestFeign, times(1)).Enseignant_ByID(1L);
        verify(enseignantRestFeign, times(1)).Enseignant_ByID(2L);
    }

    @Test
    void testGetChercheurById() {
        Chercheur chercheur = new Chercheur();
        chercheur.setId_enseignant(1L);
        when(chercheurRepository.findById(1L)).thenReturn(Optional.of(chercheur));
        when(enseignantRestFeign.Enseignant_ByID(1L)).thenReturn(null);

        Chercheur result = chercheurService.Get_ChercheurById(1L);

        assertNotNull(result);
        verify(chercheurRepository, times(1)).findById(1L);
        verify(enseignantRestFeign, times(1)).Enseignant_ByID(1L);
    }

    @Test
    void testUpdateChercheur() {
        Chercheur existing = new Chercheur();
        existing.setNom("OldNom");

        Chercheur update = new Chercheur();
        update.setNom("NewNom");

        when(chercheurRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(chercheurRepository.save(existing)).thenReturn(existing);

        Chercheur result = chercheurService.Update_Chercheur(1L, update);

        assertEquals("NewNom", result.getNom());
        verify(chercheurRepository, times(1)).findById(1L);
        verify(chercheurRepository, times(1)).save(existing);
    }
}
