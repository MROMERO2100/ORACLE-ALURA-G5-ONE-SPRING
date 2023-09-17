package com.clinic.api.domain.paciente;

import com.clinic.api.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento_identidad;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosPaciente) {
        this.nombre = datosPaciente.nombre();
        this.email = datosPaciente.email();
        this.telefono = datosPaciente.telefono();
        this.documento_identidad = datosPaciente.documento_identidad();
        this.direccion = new Direccion(datosPaciente.direccion());
    }
}
