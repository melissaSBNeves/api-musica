package sptech.projetojpa4.dto;

import sptech.projetojpa4.dominio.Compositor;

public class CompositorSimplesResponse {

    private String nome;

    private String apelido;

    public CompositorSimplesResponse(Compositor c) {
        this.nome = c.getNome();
        this.apelido = c.getApelido();
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }
}
