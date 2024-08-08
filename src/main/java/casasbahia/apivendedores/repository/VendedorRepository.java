package casasbahia.apivendedores.repository;

import casasbahia.apivendedores.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
