package com.clinic.api.domain.consulta;

import com.clinic.api.domain.medico.Medico;
import com.clinic.api.domain.medico.MedicoRepository;
import com.clinic.api.domain.paciente.PacienteRepository;
import com.clinic.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired(required = false)
    private PacienteRepository pacienteRepository;

    @Autowired(required = false)
    private MedicoRepository medicoRepository;
    @Autowired(required = false)
    private ConsultaRepository consultaRepository;
    public void agendar(DatosAgendarConsulta datos){

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("ID: "+datos.idPaciente()+" de Paciente no encontrado");
        }
        if(datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("ID: "+datos.idMedico()+" de Medico no encontrado");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);


        var consulta= new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("Especialidad no seleccionado.");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }
}
