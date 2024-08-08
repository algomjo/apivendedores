package casasbahia.apivendedores.service;

import casasbahia.apivendedores.model.Filial;
import casasbahia.apivendedores.model.TipoContratacao;
import casasbahia.apivendedores.model.Vendedor;
import casasbahia.apivendedores.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Value("${filial.api.url}")
    private String filialApiUrl;

    @Autowired
    private RestTemplate restTemplate;

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

    /*public Vendedor save(Vendedor vendedor) {
        // Buscar a filial via API mockada
        Filial filial = getFilialById(vendedor.getFilial().getId());
        vendedor.setFilial(filial);

        // Gerar matrícula automaticamente
        vendedor.setMatricula(generateMatricula(vendedor.getTipoContratacao()));

        return vendedorRepository.save(vendedor);
    }    */

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

    public Filial getFilialById(Long id) {
        String url = String.format("%s/%d", filialApiUrl, id);
        return restTemplate.getForObject(url, Filial.class);
    }


}
