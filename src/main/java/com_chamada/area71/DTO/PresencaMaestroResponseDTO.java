package com_chamada.area71.DTO;

import com_chamada.area71.model.PresencaMaestro;

public record PresencaMaestroResponseDTO(

        Long id,

        Long maestroId,

        String maestroNome,

        Boolean presente

) {

    public static PresencaMaestroResponseDTO from(PresencaMaestro presenca) {
        return new PresencaMaestroResponseDTO(
                presenca.getId(),
                presenca.getMaestro().getId(),
                presenca.getMaestro().getNome(),
                presenca.getPresente()
        );
    }

}
