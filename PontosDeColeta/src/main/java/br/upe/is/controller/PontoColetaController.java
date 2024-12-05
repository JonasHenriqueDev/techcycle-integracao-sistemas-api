package br.upe.is.controller;

import br.upe.is.domain.Item;
import br.upe.is.domain.PontoColeta;
import br.upe.is.domain.dto.ItemDTO;
import br.upe.is.domain.dto.PontoColetaDTO;
import br.upe.is.service.PontoColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PontoColetaController {

    @Autowired
    private PontoColetaService pontoColetaService;

    @GetMapping
    public List<PontoColetaDTO> getAllPontosColeta() {
        return pontoColetaService.findAll()
                .stream()
                .map(PontoColeta::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoColetaDTO> getPontoColetaById(@PathVariable Long id) {
        Optional<PontoColeta> pontoColeta = pontoColetaService.findById(id);
        return pontoColeta
                .map(pc -> ResponseEntity.ok(pc.toDTO()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PontoColetaDTO createPontoColeta(@RequestBody PontoColeta pontoColeta) {
        PontoColeta savedPontoColeta = pontoColetaService.save(pontoColeta);
        return savedPontoColeta.toDTO();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontoColetaDTO> updatePontoColeta(@PathVariable Long id, @RequestBody PontoColeta pontoColetaDetails) {
        Optional<PontoColeta> pontoColeta = pontoColetaService.findById(id);

        if (pontoColeta.isPresent()) {
            PontoColeta updatedPontoColeta = pontoColeta.get();
            updatedPontoColeta.setNome(pontoColetaDetails.getNome());
            updatedPontoColeta.setLatitude(pontoColetaDetails.getLatitude());
            updatedPontoColeta.setLongitude(pontoColetaDetails.getLongitude());
            pontoColetaService.save(updatedPontoColeta);
            return ResponseEntity.ok(updatedPontoColeta.toDTO());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePontoColeta(@PathVariable Long id) {
        Optional<PontoColeta> pontoColeta = pontoColetaService.findById(id);

        if (pontoColeta.isPresent()) {
            pontoColetaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{pontoDeColetaId}/receber-item")
    public ResponseEntity<ItemDTO> receberItem(@PathVariable Long pontoDeColetaId, @RequestBody Item item) {
        Item savedItem = pontoColetaService.receberItem(item, pontoDeColetaId);
        return ResponseEntity.ok(savedItem.toDTO());
    }

    @GetMapping("{pontoDeColetaId}/itens")
    public List<ItemDTO> getAllItens(@PathVariable Long pontoDeColetaId) {
        return pontoColetaService.findAllItensByPontoDeColetaId(pontoDeColetaId)
                .stream()
                .map(Item::toDTO)
                .toList();
    }
}
