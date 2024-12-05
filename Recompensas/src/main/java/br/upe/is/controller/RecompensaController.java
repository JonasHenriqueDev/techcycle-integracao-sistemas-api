package br.upe.is.controller;

import br.upe.is.domain.Recompensa;
import br.upe.is.service.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @GetMapping
    public ResponseEntity<List<Recompensa>> listar() {
        List<Recompensa> recompensas = recompensaService.listar();
        return ResponseEntity.ok(recompensas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> listarPorId(@PathVariable Long id) {
        Optional<Recompensa> recompensa = recompensaService.listarPorId(id);
        return recompensa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recompensa> adicionar(@RequestBody Recompensa recompensa) {
        Recompensa novaRecompensa = recompensaService.adicionarRecompensa(recompensa);
        return ResponseEntity.ok(novaRecompensa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> updateRecompensa(@PathVariable Long id, @RequestBody Recompensa recompensaDetails) {
        Optional<Recompensa> recompensa = recompensaService.listarPorId(id);

        if (recompensa.isPresent()) {
            Recompensa updatedRecompensa = recompensa.get();
            updatedRecompensa.setTitulo(recompensaDetails.getTitulo());
            updatedRecompensa.setDescricao(recompensaDetails.getDescricao());
            recompensaService.adicionarRecompensa(updatedRecompensa);
            return ResponseEntity.ok(updatedRecompensa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public String deletarPorId(@PathVariable Long id) {
        recompensaService.deletarRecompensa(id);
        return "Deletado com sucesso!";
    }
}