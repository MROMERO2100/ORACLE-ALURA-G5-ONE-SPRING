package com.clinic.api.domain.paciente;


public record DatosListadoPaciente(String nombre, String email, String documento_identidad){

        public DatosListadoPaciente(Paciente paciente) {
                this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento_identidad());
        }
}
