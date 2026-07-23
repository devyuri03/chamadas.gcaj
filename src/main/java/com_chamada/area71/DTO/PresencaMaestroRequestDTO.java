package com_chamada.area71.DTO;

import jakarta.validation.constraints.NotNull;

public record PresencaMaestroRequestDTO(

        @NotNull Long maestroId,

        @NotNull Boolean presente

) {}