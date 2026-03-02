package com.fixnow.repository;

import com.fixnow.entity.EvidenciaSolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenciaRepository extends JpaRepository<EvidenciaSolucion, Long> {
}