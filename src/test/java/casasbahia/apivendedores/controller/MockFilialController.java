package casasbahia.apivendedores.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import casasbahia.apivendedores.model.Filial;

@RestController
@RequestMapping("/api/filiais")
public class MockFilialController {

    @GetMapping("/{id}")
    public ResponseEntity<Filial> getFilial(@PathVariable Long id) {
        Filial mockFilial = new Filial(
                id,
                "Filial Teste",
                "12.345.678/0001-95",
                "Vitória",
                "ES",
                "Tipo A",
                true,
                LocalDate.now(),
                LocalDate.now()
        );
        return ResponseEntity.ok(mockFilial);
    }
}
