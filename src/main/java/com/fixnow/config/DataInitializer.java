package com.fixnow.config;

import com.fixnow.entity.Administrador;
import com.fixnow.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private AdministradorRepository adminRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepo.count() == 0) {
            Administrador admin = new Administrador();
            admin.setUsuario("admin");
            // Se guarda la contraseña encriptada (BCrypt)
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminRepo.save(admin);
            System.out.println(">>> Usuario administrador creado por defecto: admin / admin123");
        }
    }
}