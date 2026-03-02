package com.fixnow.repository;

import com.fixnow.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    List<Tecnico> findByEspecialidadContainingIgnoreCaseOrZonaContainingIgnoreCase(String especialidad, String zona);
}