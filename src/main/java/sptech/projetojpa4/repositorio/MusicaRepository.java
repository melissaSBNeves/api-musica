package sptech.projetojpa4.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sptech.projetojpa4.dominio.Musica;
import sptech.projetojpa4.dto.MusicaCompositorResponse;
import sptech.projetojpa4.dto.MusicaSimplesResponse;

import java.time.LocalDate;
import java.util.List;

/*
Uma Repository centraliza os comandos de acesso a banco de dados
de uma entidade (tabela)

JpaRepository<1,2>
1 - Classe da Entidade
2 - Classe do Id (PK) da Entidade

Não é necessário criar uma implementação dessa interface.
O Spring criará uma com os comandos SQL para o banco de dados que estivermos usando
 */
public interface MusicaRepository extends
        JpaRepository<Musica, Integer> {

    /*
    Aqui usamos a técnica de Dynamic Finder
     */
    List<Musica> findByNome(String nome);

    List<Musica> findByNomeContains(String nome);

    List<Musica> findByLancamentoAfter(LocalDate data);
    List<Musica> findTop3ByOrderByLancamento();

    /*
    Essas 2 anotações são OBRIGATÓRIAS para o caso de
    deleteBy
     */
    @Transactional // do pacote org.springframework...
    @Modifying
    long deleteByLancamentoBefore(LocalDate data);

/*
Aqui NÃO foi usado Dynamic Finder!!!
O nome do método poderia ser qualquer um!
Aqui usamos uma consulta personalizada com @Query
Esta anotação, por padrão, usa a linguagem JPQL (Java Persistence Query Language)
Ela parece com SQL mas é orientada a objetos.
Ou seja, aqui usamos os nomes de Entidades e seus atributos
nas classes Java. NÃO usamos os nomes do banco de dados

No exemplo abaixo temos 1 parâmetro (?1)
Que será substituído pelo 1º parâmetro do método
Se precisássemos de um 2o, seria ?2. De um 3o, ?3....
O NOME do parâmetro tanto faz, mas seu TIPO deve ser correto
 */
    @Query("select m.foto from Musica m where m.codigo = ?1")
    byte[] findFotoByCodigo(Integer codigo);

    /*@Query(value = "insert into tabela_nao_mapeada bla bla",
    nativeQuery = t)
    @Transactional
    @Modifying
    void insertNaoMapeada();*/

    @Query("select new Musica(m.codigo, m.nome) from Musica m")
    List<MusicaSimplesResponse> findSimples();

    @Query("select new Musica(m.codigo, m.nome, m.compositor.nome) from Musica m")
    List<MusicaCompositorResponse> findMusicaCompositor();

    @Query("select new Musica(m.codigo, m.nome, m.compositor.nome) from Musica m")
    List<MusicaCompositorResponse> findMusicaCompositor2();



    /*
Aqui usamos Dynamic Finder com Relacionamentos
     */
    List<Musica> findByCompositorApelido(String apelido);

    @Transactional
    @Modifying
    @Query("update Musica m set m.ouvintesTotal = m.ouvintesTotal+1 where m.codigo = ?1")
    int aumentarOuvintesEm1(Integer codigo);
}



