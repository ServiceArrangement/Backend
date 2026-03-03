package com.fixnow.controller;

import com.fixnow.entity.Solicitud;
import com.fixnow.entity.SolicitudImagen;
import com.fixnow.repository.SolicitudImagenRepository;
import com.fixnow.repository.SolicitudRepository;
import com.fixnow.service.SolicitudService;
import com.fixnow.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired private SolicitudService solicitudService;
    @Autowired private SolicitudRepository solicitudRepo;
    @Autowired private SolicitudImagenRepository solicitudImagenRepo;
    @Autowired private UploadService uploadService;

    // RUTA PÚBLICA: Para el Formulario Web (HTML/JS)
    @PostMapping("/public/crear")
    public Solicitud crearDesdeWeb(@RequestBody Solicitud solicitud) {
        return solicitudService.crearDesdeWeb(solicitud);
    }

    // RUTA PÚBLICA: Para crear solicitud con foto
    @PostMapping(value = "/public/crear-con-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Solicitud crearConFoto(
            @RequestParam("nombreCliente") String nombre,
            @RequestParam("telefonoCliente") String telefono,
            @RequestParam("direccion") String direccion,
            @RequestParam("tipoServicio") String tipo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen 
    ) throws IOException {
        
        Solicitud nueva = new Solicitud();
        nueva.setNombreCliente(nombre);
        nueva.setTelefonoCliente(telefono);
        nueva.setDireccion(direccion);
        nueva.setTipoServicio(tipo);
        nueva.setDescripcion(descripcion);
        nueva.setEstado("Pendiente");
        
        Solicitud guardada = solicitudRepo.save(nueva);

        // Solo intentamos subir si el archivo existe y no está vacío
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String urlImagen = uploadService.uploadImage(imagen);
                SolicitudImagen si = new SolicitudImagen();
                si.setSolicitud(guardada);
                si.setUrlImagen(urlImagen);
                solicitudImagenRepo.save(si);
            } catch (Exception e) {
                System.err.println("Error al subir a Cloudinary: " + e.getMessage());
                // Aun si falla la foto, la solicitud ya se guardó
            }
        }

        return guardada;
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