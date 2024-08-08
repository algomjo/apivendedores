package casasbahia.apivendedores.controller;

import casasbahia.apivendedores.model.Vendedor;
import casasbahia.apivendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

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

    @PostMapping
    public Vendedor createVendedor(@Validated @RequestBody Vendedor vendedor) {
        return vendedorService.save(vendedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id, @Validated @RequestBody Vendedor vendedorDetails) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        if (vendedorService.findById(id).isPresent()) {
            vendedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
