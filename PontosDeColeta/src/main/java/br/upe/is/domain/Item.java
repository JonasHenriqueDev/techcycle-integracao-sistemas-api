package br.upe.is.domain;

import br.upe.is.domain.dto.ItemDTO;
import br.upe.is.domain.dto.PontoColetaDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ponto_coleta_id", nullable = false)
    private PontoColeta pontoColeta;

    public ItemDTO toDTO() {
        ItemDTO dto = new ItemDTO();
        dto.setId(this.id);
        dto.setNome(this.nome);
        dto.setTipo(this.tipo);
        return dto;
    }

}
