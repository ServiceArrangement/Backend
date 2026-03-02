package com.fixnow.service;

import com.fixnow.entity.Solicitud;
import com.fixnow.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitudService {
    @Autowired private SolicitudRepository solicitudRepo;

    public Solicitud crearDesdeWeb(Solicitud solicitud) {
        solicitud.setEstado("Pendiente"); // Regla de negocio
        if (solicitud.getImagenes() != null) {
            solicitud.getImagenes().forEach(img -> img.setSolicitud(solicitud));
        }
        return solicitudRepo.save(solicitud);
    }

    public List<Solicitud> listarPorEstado(String estado) {
        if (estado == null || estado.isEmpty()) return solicitudRepo.findAllByOrderByFechaDesc();
        return solicitudRepo.findByEstadoOrderByFechaDesc(estado);
    }

    public Solicitud cambiarEstado(Long id, String nuevoEstado) {
        Solicitud sol = solicitudRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        sol.setEstado(nuevoEstado);
        return solicitudRepo.save(sol);
    }
}