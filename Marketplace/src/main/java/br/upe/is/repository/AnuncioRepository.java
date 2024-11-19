package br.upe.is.repository;

import br.upe.is.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByTituloContaining(String titulo);
}