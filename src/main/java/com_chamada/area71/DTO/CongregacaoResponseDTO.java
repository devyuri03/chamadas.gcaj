package com_chamada.area71.DTO;

import com_chamada.area71.model.Congregacao;

public record CongregacaoResponseDTO(

        Long id,

        String nome

) {

    public static CongregacaoResponseDTO from(Congregacao congregacao) {
        return new CongregacaoResponseDTO(congregacao.getId(), congregacao.getNome());
    }

}
