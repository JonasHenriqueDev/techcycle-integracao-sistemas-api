package br.upe.is.domain;

import br.upe.is.domain.dto.ItemDTO;
import br.upe.is.domain.dto.PontoColetaDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String longitude;
    private String latitude;

    @OneToMany(mappedBy = "pontoColeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;

    public PontoColetaDTO toDTO() {
        PontoColetaDTO dto = new PontoColetaDTO();
        dto.setId(this.id);
        dto.setNome(this.nome);
        dto.setLongitude(this.longitude);
        dto.setLatitude(this.latitude);
        if (this.itens != null) {
            dto.setItens(this.itens.stream()
                    .map(Item::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}