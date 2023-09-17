package com.clinic.api.domain.medico;

import com.clinic.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroMedico(

        @NotNull(message = "Por Favor Agregue un Nombre")
        String nombre,
        @NotNull(message = "Por Favor Agregue un Correo")
        @Email
        String email,
        @NotNull(message = "Por Favor Agregue un telefono")
        String telefono,
        @NotNull(message = "Por Favor Ingrese Numero de Documento")
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull(message = "Por Favor Agregue una especialidad")
        Especialidad especialidad,
        @NotNull(message = "Please enter direction")
        @Valid
        DatosDireccion direccion) {
}
