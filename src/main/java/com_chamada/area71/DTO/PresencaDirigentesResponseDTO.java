package com_chamada.area71.DTO;

import com_chamada.area71.model.PresencaDirigentes;

public record PresencaDirigentesResponseDTO(

        Long id,

        Long dirigenteId,

        String dirigenteNome,

        Boolean presente

) {

    public static PresencaDirigentesResponseDTO from(PresencaDirigentes presenca) {
        return new PresencaDirigentesResponseDTO(
                presenca.getId(),
                presenca.getDirigente().getId(),
                presenca.getDirigente().getNome(),
                presenca.getPresente()
        );
    }

}