package com_chamada.area71.DTO;

import com_chamada.area71.model.PresencaCongregacao;

public record PresencaCongregacaoResponseDTO(

        Long id,

        Long congregacaoId,

        String congregacaoNome,

        Integer qtdAdolescentes,

        Integer qtdJovens

) {

    public static PresencaCongregacaoResponseDTO from(PresencaCongregacao presenca) {
        return new PresencaCongregacaoResponseDTO(
                presenca.getId(),
                presenca.getCongregacao().getId(),
                presenca.getCongregacao().getNome(),
                presenca.getQtdAdolescentes(),
                presenca.getQtdJovens()
        );
    }

}
