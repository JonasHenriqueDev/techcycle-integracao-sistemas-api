package br.upe.is.service;

import br.upe.is.domain.Recompensa;
import br.upe.is.repository.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecompensaService {
    @Autowired
    private RecompensaRepository repository;

    public List<Recompensa> listar() {
        return repository.findAll();
    }

    public Optional<Recompensa> listarPorId(Long id) {
        return repository.findById(id);
    }

    public Recompensa adicionarRecompensa(Recompensa recompensa) {
        return repository.save(recompensa);
    }

    public void deletarRecompensa(Long id) {
        repository.deleteById(id);
    }
}