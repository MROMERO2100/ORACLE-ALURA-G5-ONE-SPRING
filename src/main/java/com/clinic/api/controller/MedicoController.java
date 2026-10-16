package com.clinic.api.controller;

import com.clinic.api.domain.direccion.DatosDireccion;
import com.clinic.api.domain.medico.DatosActualizarMedico;
import com.clinic.api.domain.medico.DatosListadoMedico;
import com.clinic.api.domain.medico.DatosRegistroMedico;
import com.clinic.api.domain.medico.DatosRespuestaMedico;
import com.clinic.api.domain.medico.Medico;
import com.clinic.api.domain.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(
            @RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
            UriComponentsBuilder uriComponentsBuilder) {

        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(url).body(toResponse(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(
            @PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {

        Page<DatosListadoMedico> medicos = medicoRepository
                .findByActivoTrue(paginacion)
                .map(DatosListadoMedico::new);

        return ResponseEntity.ok(medicos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(
            @RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {

        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);

        return ResponseEntity.ok(toResponse(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(toResponse(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    private DatosRespuestaMedico toResponse(Medico medico) {
        return new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
    }
}
