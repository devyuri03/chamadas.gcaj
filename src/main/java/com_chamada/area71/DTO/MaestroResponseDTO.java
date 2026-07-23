package com_chamada.area71.DTO;

import com_chamada.area71.model.Maestro;

public record MaestroResponseDTO(

        Long id,

        String nome

) {

    public static MaestroResponseDTO from(Maestro maestro) {
        return new MaestroResponseDTO(maestro.getId(), maestro.getNome());
    }

}
