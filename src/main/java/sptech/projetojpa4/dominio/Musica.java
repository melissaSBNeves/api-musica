package sptech.projetojpa4.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
@Entity -> Indica que a classe é uma Entidade
            Ou seja, ela mapeia, "espelha" uma tabela
 */
@Entity
public class Musica {

    @Id // do pacote jakarta.persistence -> indica que este atributo é o "id", ou seja, a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que a chave primária é auto-incremento
    private Integer codigo;

    @NotBlank
    @Size(min = 2, max = 30)
    private String nome;

    private Integer ouvintesTotal;


    public Musica() {
    }

    // LocalDate: usamos data no formato ISO: aaaa-mm-dd
    // ex: "2000-12-31"
    // @NotNull
    private LocalDate lancamento;

    private String estilo; // criar get e set

    private Boolean classico; // criar get e set

    /*
 @Column - indica as configs de banco de dados do atributo mapeado
     */
    @JsonIgnore
    @Column(length = 20 * 1024 * 1024) // tamanho de 20 MB
    private byte[] foto;

    @ManyToOne // indica um relacionamento (FK)
    private Compositor compositor;

    public Musica(Integer codigo, String nome, Compositor compositor) {
        this.codigo = codigo;
        this.nome = nome;
        this.compositor = compositor;
    }

    public Musica(Integer codigo, String nome, String nomeCompositor) {
        this.codigo = codigo;
        this.nome = nome;
        this.compositor = new Compositor(nomeCompositor);
    }


    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Boolean getClassico() {
        return classico;
    }

    public void setClassico(Boolean classico) {
        this.classico = classico;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    // todos os campos precisam ter get e set para que funcionem corretamente
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

    public Integer getOuvintesTotal() {
        return ouvintesTotal;
    }

    public void setOuvintesTotal(Integer ouvintesTotal) {
        this.ouvintesTotal = ouvintesTotal;
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Compositor getCompositor() {
        return compositor;
    }

    public void setCompositor(Compositor compositor) {
        this.compositor = compositor;
    }
}
