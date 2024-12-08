package dcc.tp2.enseignantservice.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.service.EnseignantService;
import dcc.tp2.enseignantservice.web.API;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(API.class)
class APITest {

    @MockBean
    private EnseignantService enseignantService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    List<Enseignant> enseignantList;

    @BeforeEach
    void setUp(){
        this.enseignantList= List.of(
                new Enseignant(1L,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant"),
                new Enseignant(2L,"ali","ahmadi","KJ8888","ali@mail.com","ali123","informatique","Enseignant")
        );
    }

    @Test
    void add() throws Exception {
        Enseignant enseignant = new Enseignant(null,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant");

        Mockito.when(enseignantService.Create_Enseignant(Mockito.any(Enseignant.class))).thenReturn(enseignantList.get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/Enseignants")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(enseignant)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList.get(0))));
    }

    @Test
    void getALL() throws Exception {

        Mockito.when(enseignantService.GetAll_Enseignant()).thenReturn(enseignantList);

        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList)));


    }

    @Test
    void get_ByID() throws Exception{
        Long id = 1L;
        Mockito.when(enseignantService.Get_EnseignantByID(id)).thenReturn(enseignantList.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList.get(0))));
    }


    @Test
    void update() throws Exception {
        Enseignant enseignant = new Enseignant(1l,"oussama","alaoui","la1223","oussama@mail.com","oussama123","informatique","Enseignant");
        Long id = 1L;
        Mockito.when(enseignantService.Update_Enseignant(Mockito.any(Enseignant.class),Mockito.anyLong())).thenReturn(enseignantList.get(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/Enseignants/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(enseignant)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(enseignantList.get(0))));

    }

    @Test
    void delete() throws Exception {
        Long id=1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/Enseignants/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void statistique() throws Exception {
        Long id = 1L;

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",2L);
        Statistiques.put("nombre de chercheur",2L);

        Mockito.when(enseignantService.statistique(Mockito.anyLong())).thenReturn(Statistiques);
        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants/statistique/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Statistiques)));
    }

    @Test
    void getByemail() throws Exception {

        String email = "oussama@mail.com";
        Mockito.when(enseignantService.FindByEmail(email)).thenReturn(enseignantList.get(0));

        Map<String, String> infos_user = new HashMap<>();
        infos_user.put("email", enseignantList.get(0).getEmail());
        infos_user.put("password", enseignantList.get(0).getPassword());
        infos_user.put("scope", enseignantList.get(0).getRole());

        mockMvc.perform(MockMvcRequestBuilders.get("/Enseignants/email/{email}",email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(infos_user)));

    }
}