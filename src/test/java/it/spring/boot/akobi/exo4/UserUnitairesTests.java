package it.spring.boot.akobi.exo4;


import it.spring.boot.akobi.exo4.controller.InscriptionController;
import it.spring.boot.akobi.exo4.service.UtilisateursService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = InscriptionController.class)
public class UserUnitairesTests {

    @MockBean
    private UtilisateursService utilisateursService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testSubmitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/inscription")
                        .param("nom", "John Doe")
                        .param("prenom", "johndoe")
                        .param("email", "johndoe@example.com")
                        .param("password", "1234567890")
                        .param("passwordsecond", "1234567890")
                )
//                        .param("message", "Hello, World!")
                .andExpect(MockMvcResultMatchers.status().is(302));
//                .andExpect(MockMvcResultMatchers.content().string("Form submitted successfully"))
    }
}
