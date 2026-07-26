package com_chamada.area71.DTO;

import jakarta.validation.constraints.NotNull;

public record PresencaDirigentesRequestDTO(

        @NotNull Long dirigenteId,

        @NotNull Boolean presente

) {}