package com.clinic.api.domain.consulta;

import com.clinic.api.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        @NotNull(message = "Falta la Fecha")
        @Future
        //PARA ESTABLECER UN FORMATO REQUERIDO DE FECHA Y HORA
        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha,

        Especialidad especialidad) {
}
