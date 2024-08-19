package br.edu.unipe.api.controller;

import br.edu.unipe.api.service.TelefoneService;
import br.edu.unipe.api.model.dto.TelefoneDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.unipe.api.model.Telefone;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    private final TelefoneService service;

    //busca por id
    @GetMapping("/{id}")
    public TelefoneDTO consultarFonePorId(@PathVariable int id) {
        log.info("Inicio - Consulta telefone id {} ", id);
        var telefoneDTO = service.consultar(id);
        log.info("Fim  - Consulta telefone id {} ", id);
        return telefoneDTO;
    }

    //lista todos os fones
    @GetMapping
    public List<Telefone> listar() {
        log.info("Listando Telefones");
        return service.listarTelefones();
    }

    @GetMapping("/usuarioId")
    public List<Telefone> listarPorUsuarioId(@PathVariable int usuarioId) {
        log.info("Listando Telefones por usuarioId");
        return service.buscarPorUsuarioId(usuarioId);
    }

    @GetMapping("/ddd/{ddd}")
    public List<Telefone> listarPorDdd(@PathVariable String ddd) {
        log.info("Listando Telefones por ddd");
        return service.listarPorDdd(ddd);
    }

    @PostMapping
    public ResponseEntity<TelefoneDTO> salvar(@RequestBody TelefoneDTO telefoneDTO) {
        telefoneDTO = service.salvar(telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneDTO);
    }

    @PutMapping
    public Telefone atualizar(@RequestBody Telefone telefone) {
        return service.alterar(telefone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefone(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
