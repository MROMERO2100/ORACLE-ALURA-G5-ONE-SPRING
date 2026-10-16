package com.clinic.api.controller;

import com.clinic.api.domain.paciente.DatosListadoPaciente;
import com.clinic.api.domain.paciente.DatosRegistroPaciente;
import com.clinic.api.domain.paciente.Paciente;
import com.clinic.api.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listPacientes(
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {

        Page<DatosListadoPaciente> pacientes = pacienteRepository
                .findAll(paginacion)
                .map(DatosListadoPaciente::new);

        return ResponseEntity.ok(pacientes);
    }
}
