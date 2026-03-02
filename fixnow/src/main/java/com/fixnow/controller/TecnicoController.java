package com.fixnow.controller;

import com.fixnow.entity.Tecnico;
import com.fixnow.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@CrossOrigin(origins = "*")
public class TecnicoController {

    @Autowired private TecnicoService tecnicoService;

    @GetMapping
    public List<Tecnico> listar() {
        return tecnicoService.listarTodos();
    }

    @GetMapping("/buscar")
    public List<Tecnico> buscar(@RequestParam String query) {
        return tecnicoService.buscar(query);
    }

    @PostMapping
    public Tecnico guardar(@RequestBody Tecnico tecnico) {
        return tecnicoService.guardar(tecnico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}