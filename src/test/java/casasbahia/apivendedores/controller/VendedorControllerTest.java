package casasbahia.apivendedores.controller;

import casasbahia.apivendedores.model.Filial;
import casasbahia.apivendedores.service.VendedorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;


@SpringBootTest
@AutoConfigureMockMvc
public class VendedorControllerTest {

    @Mock
    private VendedorService vendedorService;

    @InjectMocks
    private VendedorController vendedorController;

    @Autowired
    private MockMvc mockMvc;

      @Test
    public void testGetAllVendedores() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendedorController).build();

        when(vendedorService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/vendedores"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(vendedorService, times(1)).findAll();
    }

    @Test
    public void testCreateVendedor() throws Exception {
        // JSON com os dados da filial para teste
        String filialJson = "{ \"id\": 1, \"nome\": \"Filial Teste\", \"cnpj\": \"12.345.678/0001-95\", \"cidade\": \"Vitória\", \"uf\": \"ES\", \"tipo\": \"Tipo A\", \"ativo\": true, \"dataCadastro\": \"" + LocalDate.now() + "\", \"ultimaAtualizacao\": \"" + LocalDate.now() + "\" }";

        // JSON com os dados do vendedor para teste
        String vendedorJson = "{ \"nome\": \"João Silva\", \"dataNascimento\": \"1985-03-15\", \"documento\": \"12345678900\", \"email\": \"joao.silva@example.com\", \"tipoContratacao\": \"CLT\", \"filial\": \"Filial Teste\" }";

         // Mock da resposta da API de filiais
        mockMvc.perform(MockMvcRequestBuilders.get("/api/filiais/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(filialJson));

        ResultActions resultActions = mockMvc.perform(post("/vendedores/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(vendedorJson))
            .andExpect(status().isCreated());

        // Verifica se o vendedor retornado possui os valores esperados
        resultActions.andExpect(jsonPath("$.nome").value("João Silva"))
                 .andExpect(jsonPath("$.documento").value("12345678900"))
                 .andExpect(jsonPath("$.email").value("joao.silva@example.com"))
                 .andExpect(jsonPath("$.tipoContratacao").value("CLT"))
                 .andExpect(jsonPath("$.filial").value("Filial Teste"))
                 .andExpect(jsonPath("$.matricula").exists()); // Verifica se a matrícula foi gerada


    }
    
    @Test
    public void testCreateVendedorMock() throws Exception {
        // Configura o comportamento esperado do serviço mockado
        when(vendedorService.getFilialById(anyLong())).thenReturn(new Filial(1L, "Filial Teste", "12.345.678/0001-95", "Vitória", "ES", "Tipo A", true, LocalDate.now(), LocalDate.now()));

        // JSON com os dados da filial para teste
        String filialJson = "{ \"id\": 1, \"nome\": \"Filial Teste\", \"cnpj\": \"12.345.678/0001-95\", \"cidade\": \"Vitória\", \"uf\": \"ES\", \"tipo\": \"Tipo A\", \"ativo\": true, \"dataCadastro\": \"" + LocalDate.now() + "\", \"ultimaAtualizacao\": \"" + LocalDate.now() + "\" }";
    
        // JSON com os dados do vendedor para teste, com o campo filial como objeto
        String vendedorJson = "{ \"nome\": \"João Silva\", \"dataNascimento\": \"1985-03-15\", \"documento\": \"12345678900\", \"email\": \"joao.silva@example.com\", \"tipoContratacao\": \"CLT\", \"filial\": \"Filial Teste\" }";
        
    
        // Configura o MockMvc para responder com o JSON da filial
        mockMvc.perform(MockMvcRequestBuilders.get("/api/filiais/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(filialJson));
        
        ResultActions resultActions = mockMvc.perform(post("/vendedores/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(vendedorJson))
            .andExpect(status().isCreated());
    
            
        // Verifica se o vendedor retornado possui os valores esperados
        resultActions.andExpect(jsonPath("$.nome").value("João Silva"))
                 .andExpect(jsonPath("$.documento").value("12345678900"))
                 .andExpect(jsonPath("$.email").value("joao.silva@example.com"))
                 .andExpect(jsonPath("$.tipoContratacao").value("CLT"))
                 .andExpect(jsonPath("$.filial").value("Filial Teste"))  // Verificando que o nome da filial está correto
                 .andExpect(jsonPath("$.matricula").exists()); // Verifica se a matrícula foi gerada
    }
    

}
