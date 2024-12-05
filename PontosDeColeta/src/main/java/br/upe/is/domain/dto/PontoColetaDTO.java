package br.upe.is.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PontoColetaDTO {
    private Long id;
    private String nome;
    private String longitude;
    private String latitude;
    private List<ItemDTO> itens;
}