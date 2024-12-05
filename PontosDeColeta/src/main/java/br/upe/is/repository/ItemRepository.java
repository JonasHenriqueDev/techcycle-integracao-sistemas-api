package br.upe.is.repository;

import br.upe.is.domain.Item;
import br.upe.is.domain.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllItensByPontoColeta(PontoColeta pontoColeta);
}
