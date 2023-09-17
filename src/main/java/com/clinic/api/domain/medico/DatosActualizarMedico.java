package com.clinic.api.domain.medico;

import com.clinic.api.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;


public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}

