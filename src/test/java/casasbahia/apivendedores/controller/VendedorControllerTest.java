package casasbahia.apivendedores.controller;

import casasbahia.apivendedores.service.VendedorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class VendedorControllerTest {

    @Mock
    private VendedorService vendedorService;

    @InjectMocks
    private VendedorController vendedorController;

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


}
