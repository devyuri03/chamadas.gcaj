package com_chamada.area71.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record EnsaioRequestDTO(

        @NotNull(message = "A data do ensaio é obrigatória")
        LocalDate data,

        @NotEmpty(message = "Informe ao menos uma congregação")
        @Valid
        List<PresencaCongregacaoRequestDTO> congregacoes,

        @Valid
        List<PresencaMaestroRequestDTO> maestros

) {
}