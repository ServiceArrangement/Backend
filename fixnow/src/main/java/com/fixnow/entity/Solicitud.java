package com.fixnow.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "solicitudes")
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "telefono_cliente")
    private String telefonoCliente;

    private String direccion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tipo_servicio")
    private String tipoServicio;

    private String estado; // Pendiente, En Proceso, Finalizado

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudImagen> imagenes;
}