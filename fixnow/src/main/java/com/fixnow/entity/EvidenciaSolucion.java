package com.fixnow.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "evidencias_solucion")
@Data
public class EvidenciaSolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // Una evidencia por cada solicitud finalizada
    @JoinColumn(name = "solicitud_id")
    private Solicitud solicitud;

    @Column(name = "url_imagen", columnDefinition = "TEXT")
    private String urlImagen;

    @Column(columnDefinition = "TEXT")
    private String comentario;
}