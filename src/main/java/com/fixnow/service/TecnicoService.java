package com.fixnow.service;

import com.fixnow.entity.Tecnico;
import com.fixnow.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TecnicoService {
    @Autowired private TecnicoRepository tecnicoRepo;

    public List<Tecnico> listarTodos() {
        return tecnicoRepo.findAll();
    }

    public List<Tecnico> buscar(String query) {
        return tecnicoRepo.findByEspecialidadContainingIgnoreCaseOrZonaContainingIgnoreCase(query, query);
    }

    public Tecnico guardar(Tecnico tecnico) {
        // Al guardar, JPA manejará también los objetos TecnicoDocumento si vienen en la lista
        if (tecnico.getDocumentos() != null) {
            tecnico.getDocumentos().forEach(doc -> doc.setTecnico(tecnico));
        }
        return tecnicoRepo.save(tecnico);
    }

    public void eliminar(Long id) {
        // Regla de Negocio: Podrías verificar aquí si el técnico está asignado a solicitudes finalizadas
        // Por ahora, validamos existencia básica
        if (!tecnicoRepo.existsById(id)) throw new RuntimeException("El técnico no existe");
        tecnicoRepo.deleteById(id);
    }
}