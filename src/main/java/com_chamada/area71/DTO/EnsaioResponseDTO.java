package com_chamada.area71.DTO;

import com_chamada.area71.model.Ensaio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EnsaioResponseDTO(

        Long id,

        LocalDate data,

        LocalDateTime criadoEm,

        List<PresencaCongregacaoResponseDTO> congregacoes,

        List<PresencaMaestroResponseDTO> maestros,

        List<PresencaDirigentesResponseDTO> dirigentes

) {

    public static EnsaioResponseDTO from(Ensaio ensaio) {
        return new EnsaioResponseDTO(
                ensaio.getId(),
                ensaio.getData(),
                ensaio.getCriadoEm(),
                ensaio.getPresencasCongregacao().stream()
                        .map(PresencaCongregacaoResponseDTO::from)
                        .toList(),
                ensaio.getPresencasMaestro().stream()
                        .map(PresencaMaestroResponseDTO::from)
                        .toList(),
                ensaio.getPresencasDirigentes().stream()
                        .map(PresencaDirigentesResponseDTO::from)
                        .toList()
        );
    }

}
