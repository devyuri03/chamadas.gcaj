package com_chamada.area71.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PresencaCongregacaoRequestDTO(

        @NotNull Long congregacaoId,

        @NotNull @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer qtdAdolescentes,

        @NotNull @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer qtdJovens

) {}