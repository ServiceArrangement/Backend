package com.fixnow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tecnico_documentos")
@Data
public class TecnicoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    @JsonIgnore // Para evitar bucles infinitos en el JSON
    private Tecnico tecnico;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "url_archivo", columnDefinition = "TEXT")
    private String urlArchivo;
}