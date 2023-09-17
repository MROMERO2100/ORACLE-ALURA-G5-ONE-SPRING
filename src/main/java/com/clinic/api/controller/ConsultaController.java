package com.clinic.api.controller;

import com.clinic.api.domain.consulta.ConsultaService;
import com.clinic.api.domain.consulta.DatosAgendarConsulta;
import com.clinic.api.domain.consulta.DatosDetalleConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired(required = false)
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        service.agendar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));
    }
}
