package br.upe.is.service;


import br.upe.is.domain.Item;
import br.upe.is.domain.PontoColeta;
import br.upe.is.repository.ItemRepository;
import br.upe.is.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private NotificationProducer notificationProducer;

    public List<PontoColeta> findAll() {
        return pontoColetaRepository.findAll();
    }

    public Optional<PontoColeta> findById(Long id) {
        return pontoColetaRepository.findById(id);
    }

    public PontoColeta save(PontoColeta pontoColeta) {
        PontoColeta savedPontoColeta = pontoColetaRepository.save(pontoColeta);
        notificationProducer.notifyNewCollectionPoint(savedPontoColeta);
        return savedPontoColeta;
    }

    public void deleteById(Long id) {
        pontoColetaRepository.deleteById(id);
    }


    public Item receberItem(Item item, Long pontoDeColetaId) {
        Optional<PontoColeta> pontoId = pontoColetaRepository.findById(pontoDeColetaId);

        if (pontoId.isEmpty()) {
            throw new IllegalArgumentException("Ponto de coleta não encontrado com o ID: " + pontoId);
        }

        item.setPontoColeta(pontoId.get());
        Item savedItem = itemRepository.save(item);

        notificationProducer.notifyNewItemReceived(item);

        return savedItem;
    }

    public List<Item> findAllItensByPontoDeColetaId(Long pontoDeColetaId) {
        Optional<PontoColeta> pontoColeta = pontoColetaRepository.findById(pontoDeColetaId);
        return itemRepository.findAllItensByPontoColeta(pontoColeta.get());
    }
}