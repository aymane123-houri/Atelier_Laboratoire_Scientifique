package dcc.tp2.enseignantservice.ControllerTest;

import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.service.EnseignantService;
import dcc.tp2.enseignantservice.web.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class APITest {

    @Mock
    private EnseignantService enseignantService;

    @InjectMocks
    private API api;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEnseignant() {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("John");

        when(enseignantService.Create_Enseignant(any(Enseignant.class))).thenReturn(enseignant);

        ResponseEntity<Enseignant> response = api.add(enseignant);
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getNom());
    }

    @Test
    void testGetAll() {
        List<Enseignant> enseignants = new ArrayList<>();
        enseignants.add(new Enseignant());

        when(enseignantService.GetAll_Enseignant()).thenReturn(enseignants);

        ResponseEntity<List<Enseignant>> response = api.GetALL();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetByEmail() {
        Enseignant enseignant = new Enseignant();
        enseignant.setEmail("john.doe@example.com");
        enseignant.setPassword("password123");
        enseignant.setRole("ROLE_ENSEIGNANT");

        when(enseignantService.FindByEmail("john.doe@example.com")).thenReturn(enseignant);

        ResponseEntity<Map<String, String>> response = api.getByemail("john.doe@example.com");
        assertNotNull(response.getBody());
        assertEquals("john.doe@example.com", response.getBody().get("email"));
        assertEquals("password123", response.getBody().get("password"));
        assertEquals("ROLE_ENSEIGNANT", response.getBody().get("scope"));
    }
}
