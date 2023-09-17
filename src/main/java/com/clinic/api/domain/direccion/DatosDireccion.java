package com.clinic.api.domain.direccion;

import jakarta.validation.constraints.NotNull;

public record DatosDireccion(
        @NotNull(message = "Please enter Calle")
        String calle,
        @NotNull(message = "Please enter numero")
        String numero,
        @NotNull(message = "Please enter Distrito")
        String distrito,
        @NotNull(message = "Please enter Ciudad")
        String ciudad,

        @NotNull(message = "Please enter Complemento")
        String complemento) {

}
