package com.fixnow.service;

import com.fixnow.config.JwtUtil;
import com.fixnow.dto.AuthRequest;
import com.fixnow.dto.AuthResponse;
import com.fixnow.entity.Administrador;
import com.fixnow.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private AdministradorRepository adminRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request) {
        Administrador admin = adminRepo.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            String token = jwtUtil.generateToken(admin.getUsuario());
            return new AuthResponse(token, admin.getUsuario());
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}