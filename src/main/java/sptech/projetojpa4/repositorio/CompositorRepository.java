package sptech.projetojpa4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.projetojpa4.dominio.Compositor;
import sptech.projetojpa4.dto.CompositorSimplesResponse;
import sptech.projetojpa4.dto.RelatorioCompositorResponse;

import java.util.List;

public interface CompositorRepository extends JpaRepository<Compositor, Integer> {


    @Query("select new Compositor(c.nome, c.apelido) from Compositor c")
    List<CompositorSimplesResponse> findCompositorApelido();



}
