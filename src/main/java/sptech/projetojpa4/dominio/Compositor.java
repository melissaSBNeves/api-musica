package sptech.projetojpa4.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Compositor {

    @Id // do pacote jakarta.persistence -> indica que este atributo é o "id", ou seja, a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que a chave primária é auto-incremento
    private Integer codigo;

    private String nome;

    private String apelido;

    public Compositor(String nome) {
        this.nome = nome;
    }

    public Compositor() {
    }

    public Compositor(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
