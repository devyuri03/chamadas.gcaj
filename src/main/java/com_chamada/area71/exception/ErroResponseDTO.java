package com_chamada.area71.exception;

import java.util.Map;

public record ErroResponseDTO(

        int status,

        String message,

        Map<String, String> erros

) {

    public ErroResponseDTO(int status, String message) {
        this(status, message, null);
    }

}
