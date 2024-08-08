package casasbahia.apivendedores.service;

import casasbahia.apivendedores.model.TipoContratacao;
import casasbahia.apivendedores.model.Vendedor;
import casasbahia.apivendedores.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> findById(Long id) {
        return vendedorRepository.findById(id);
    }

    public Vendedor save(Vendedor vendedor) {
        // Gerar matrícula automaticamente
        vendedor.setMatricula(generateMatricula(vendedor.getTipoContratacao()));
        return vendedorRepository.save(vendedor);
    }

    public void deleteById(Long id) {
        vendedorRepository.deleteById(id);
    }

    private String generateMatricula(TipoContratacao tipoContratacao) {
        // Lógica para gerar matrícula
        long sequencial = vendedorRepository.count() + 1;
        String sufixo = tipoContratacao == TipoContratacao.OUTSOURCING ? "OUT" :
                        tipoContratacao == TipoContratacao.CLT ? "CLT" :
                        "PJ";
        return String.format("%08d-%s", sequencial, sufixo);
    }
}
