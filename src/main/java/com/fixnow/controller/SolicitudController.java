package com.fixnow.controller;

import com.fixnow.entity.Solicitud;
import com.fixnow.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired private SolicitudService solicitudService;

    // RUTA PÚBLICA: Para el Formulario Web (HTML/JS)
    @PostMapping("/public/crear")
    public Solicitud crearDesdeWeb(@RequestBody Solicitud solicitud) {
        return solicitudService.crearDesdeWeb(solicitud);
    }

    // RUTAS PRIVADAS: Para el Administrador (App Flutter)
    @GetMapping
    public List<Solicitud> listar(@RequestParam(required = false) String estado) {
        return solicitudService.listarPorEstado(estado);
    }

    @PatchMapping("/{id}/estado")
    public Solicitud cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return solicitudService.cambiarEstado(id, nuevoEstado);
    }
}