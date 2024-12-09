package dcc.tp2.enseignantservice.ChercheurControllerService;

import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.service.ChercheurService;
import dcc.tp2.chercheurservice.web.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class APITest {

    @Mock
    private ChercheurService chercheurService;

    @InjectMocks
    private API api;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddChercheur() {
        Chercheur chercheur = new Chercheur();
        chercheur.setNom("TestNom");
        when(chercheurService.Create_Chercheur(chercheur)).thenReturn(chercheur);

        ResponseEntity<Chercheur> response = api.add(chercheur);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("TestNom", response.getBody().getNom());
        verify(chercheurService, times(1)).Create_Chercheur(chercheur);
    }

    @Test
    void testGetAllChercheurs() {
        when(chercheurService.GetALL_Chercheur()).thenReturn(List.of(new Chercheur(), new Chercheur()));

        ResponseEntity<List<Chercheur>> response = api.GetAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(chercheurService, times(1)).GetALL_Chercheur();
    }

    @Test
    void testGetById() {
        Chercheur chercheur = new Chercheur();
        chercheur.setNom("TestNom");
        when(chercheurService.Get_ChercheurById(1L)).thenReturn(chercheur);

        ResponseEntity<Chercheur> response = api.GetById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("TestNom", response.getBody().getNom());
        verify(chercheurService, times(1)).Get_ChercheurById(1L);
    }

    @Test
    void testGetByEmail() {
        Chercheur chercheur = new Chercheur();
        chercheur.setEmail("test@test.com");
        chercheur.setPassword("password");
        chercheur.setRole("ROLE_USER");
        when(chercheurService.Get_ChercheurByEmail("test@test.com")).thenReturn(chercheur);

        ResponseEntity<Map<String, String>> response = api.getByemail("test@test.com");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("test@test.com", response.getBody().get("email"));
        assertEquals("password", response.getBody().get("password"));
        assertEquals("ROLE_USER", response.getBody().get("scope"));
        verify(chercheurService, times(1)).Get_ChercheurByEmail("test@test.com");
    }
}