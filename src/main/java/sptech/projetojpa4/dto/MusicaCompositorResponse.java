package sptech.projetojpa4.dto;

import sptech.projetojpa4.dominio.Compositor;
import sptech.projetojpa4.dominio.Musica;

public class MusicaCompositorResponse {

    private Integer codigo;

    private String nome;

    private String compositor;
    public MusicaCompositorResponse(Musica m) {
        this.codigo = m.getCodigo();
        this.nome = m.getNome();
        this.compositor = m.getCompositor().getNome();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCompositor() {
        return compositor;
    }
}
