package sptech.projetojpa4.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.projetojpa4.dominio.Compositor;
import sptech.projetojpa4.dto.CompositorSimplesResponse;
import sptech.projetojpa4.dto.RelatorioCompositorResponse;
import sptech.projetojpa4.repositorio.CompositorRepository;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/compositores")
public class CompositorController {

    @Autowired
    private CompositorRepository compositorRepository;


    @GetMapping("/simples")
    public ResponseEntity<List<CompositorSimplesResponse>> getCompositorSimples(){
        List<CompositorSimplesResponse> lista = compositorRepository.findCompositorApelido();
        return lista.isEmpty() ? status(204).build() : status(200).body(lista);
    }


    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioCompositorResponse> getRelatorio(){
        return status(200).body(new RelatorioCompositorResponse(compositorRepository.count()));

    }
}
