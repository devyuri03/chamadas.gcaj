package com_chamada.area71.DTO;

import com_chamada.area71.model.Dirigentes;

public record DirigentesResponseDTO(

        Long id,

        String nome

) {

    public static DirigentesResponseDTO from(Dirigentes dirigente) {
        return new DirigentesResponseDTO(dirigente.getId(), dirigente.getNome());
    }

}