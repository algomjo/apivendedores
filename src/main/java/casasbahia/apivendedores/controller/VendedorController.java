package casasbahia.apivendedores.controller;

import casasbahia.apivendedores.model.Vendedor;
import casasbahia.apivendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private static final Logger logger = LoggerFactory.getLogger(VendedorController.class);


    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        return vendedor.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Vendedor> createVendedor(@Validated @RequestBody Vendedor vendedor) {
    logger.info("Criando vendedor: {}", vendedor);    
    Vendedor savedVendedor = vendedorService.save(vendedor); // Salva o vendedor e armazena o resultado
    return ResponseEntity
            .status(HttpStatus.CREATED) // Retorna o status 201
            .body(savedVendedor);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id, @Validated @RequestBody Vendedor vendedorDetails) {
        logger.info("Atualizando vendedor: {}", vendedorDetails);    
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        if (vendedor.isPresent()) {
            Vendedor updatedVendedor = vendedor.get();
            updatedVendedor.setNome(vendedorDetails.getNome());
            updatedVendedor.setDataNascimento(vendedorDetails.getDataNascimento());
            updatedVendedor.setDocumento(vendedorDetails.getDocumento());
            updatedVendedor.setEmail(vendedorDetails.getEmail());
            updatedVendedor.setTipoContratacao(vendedorDetails.getTipoContratacao());
            updatedVendedor.setFilial(vendedorDetails.getFilial());
            return ResponseEntity.ok(vendedorService.save(updatedVendedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        if (vendedorService.findById(id).isPresent()) {
            vendedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
