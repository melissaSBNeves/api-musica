package sptech.projetojpa4.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa4.dominio.Musica;
import sptech.projetojpa4.dto.MusicaCompositorResponse;
import sptech.projetojpa4.dto.MusicaSimplesResponse;
import sptech.projetojpa4.repositorio.MusicaRepository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

/*
@Autowired -> Indica que é de responsabilidade do Spring em instanciar o objeto,
no caso, o 'repository'.
Assim, quando qualquer dos método da classe precisar do 'repository', ele já terá um valor válido
 */
    @Autowired
    private MusicaRepository repository;

    @GetMapping
    public ResponseEntity<List<Musica>> get() {
        // findAll() -> equivale a um "select * from tabela"
        var lista = repository.findAll();
//        List<Musica> lista = repository.findAll();
        return lista.isEmpty()
                ? status(204).build()
                : status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Musica> post(@RequestBody @Valid Musica novaMusica) {
        // save() -> equivale a um "insert into tabela" ou a um "update tabela". Se o campo que for a Id será um insert, caso contrário, um update
        repository.save(novaMusica);
        return status(201).body(novaMusica);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Musica> get(@PathVariable Integer codigo) {
        /*
ResponseEntity.of() -> retorna, automaticamente:
404 e sem corpo, se seu argumento não tiver valor
200 e com corpo, se seu argumento tiver valor, que será o corpo da resposta
         */
        return of(repository.findById(codigo));
        // return of(repository.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
        // existsById() -> retorna true se o valor é uma PK que existe para a entidade
        if (repository.existsById(codigo)) {
            // deleteById() -> equivale a um "delete from tabela where id = ?"
            repository.deleteById(codigo);
            return status(204).build();
        }
        return status(404).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Musica> put(@PathVariable Integer codigo,
                      @RequestBody @Valid Musica musica) {
        if (repository.existsById(codigo)) {
            musica.setCodigo(codigo);
            repository.save(musica);
            return status(200).body(musica);
        }

        return status(404).build();
    }

    @GetMapping("/filtro-nome/{nome}")
    public ResponseEntity<List<Musica>> fitroNome(
      @PathVariable String nome
    ) {
        var lista = repository.findByNomeContains(nome);

        return lista.isEmpty()
                ? status(204).build()
                : status(200).body(lista);
    }

    @GetMapping("/lancadas-apos/{lancamento}")
    public ResponseEntity<List<Musica>> fitroData(
            @PathVariable LocalDate lancamento)
    {
        var lista = repository.findByLancamentoAfter(lancamento);

        return lista.isEmpty()
                ? status(204).build()
                : status(200).body(lista);
    }

    @GetMapping("/top3-antigas")
    public ResponseEntity<List<Musica>> top3Antigas()
    {
        var lista = repository.findTop3ByOrderByLancamento();

        return lista.isEmpty()
                ? status(204).build()
                : status(200).body(lista);
    }

    @PatchMapping(value = "/foto/{codigo}",
            consumes = {"image/jpeg", "image/png", "image/webp", "image/gif"})
//    consumes = "image/*")
//    consumes = MediaType.IMAGE_JPEG_VALUE)
    //consumes = "image/jpeg")
    public ResponseEntity<Void> patchFoto(
            @PathVariable Integer codigo, @RequestBody byte[] novaFoto
    ) {
        var musica = repository.findById(codigo).get();
        musica.setFoto(novaFoto);
        repository.save(musica);

        return status(204).build();
    }

/*
produces -> indica o "content-type" da resposta
 */
    @GetMapping(value = "/foto/{codigo}",
                produces = MediaType.IMAGE_JPEG_VALUE)
//                produces = "image/jpeg")
    public ResponseEntity<byte[]> getFoto(
            @PathVariable Integer codigo){
//        var musica = repository.findById(codigo).get();
//        return status(200).body(musica.getFoto());
        return status(200).body(repository.findFotoByCodigo(codigo));
    }

    @GetMapping("/filtro-compositor/{apelido}")
    public ResponseEntity<List<Musica>> fitroCompositor(
            @PathVariable String apelido
    ) {
        var lista = repository.findByCompositorApelido(apelido);

        return lista.isEmpty()
                ? status(204).build()
                : status(200).body(lista);
    }



    @GetMapping("/simples")
    public ResponseEntity<List<MusicaSimplesResponse>> getSimples(){
        return repository.findSimples().isEmpty() ? status(204).build() : status(200).body(repository.findSimples());
    }

    @GetMapping("/musica-compositor")
    public ResponseEntity<List<MusicaCompositorResponse>> getMusicaCompositor(){
        return repository.findMusicaCompositor().isEmpty() ? status(204).build() : status(200).body(repository.findMusicaCompositor());
    }

//    @PatchMapping("/{codigo}/mais-ouvintes")
//    public ResponseEntity<Musica> maisOuvinte(@PathVariable Integer codigo){
//        if(!repository.existsById(codigo)){
//            return status(404).build();
//        }
//        Musica m = repository.findById(codigo).get();
//        m.setOuvintesTotal(m.getOuvintesTotal() + 1);
//        m.setCodigo(codigo);
//        repository.save(m);
//        return status(204).build();
//    }

    @PatchMapping("/{codigo}/mais-ouvintes")
    public ResponseEntity<Void> maisOuvintes(@PathVariable Integer codigo){

        var alterado = repository.aumentarOuvintesEm1(codigo);
        return alterado == 1 ? status(204).build() : status(404).build();
    }
}





