package com.clinic.api.domain.paciente;
import com.clinic.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroPaciente(

        @NotNull(message = "Por Favor Agregue un Nombre")
        String nombre,
        @NotNull(message = "Por Favor Agregue un Correo")
        @Email
        String email,
        @NotNull(message = "Por Favor Agregue un telefono")
        String telefono,
        @NotNull(message = "Por Favor Documento")
        String documento_identidad,
        @NotNull(message = "Please enter direction")
        @Valid
        DatosDireccion direccion) {
}
