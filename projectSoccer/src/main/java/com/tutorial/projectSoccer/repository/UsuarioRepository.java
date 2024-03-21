package com.tutorial.projectSoccer.repository;

import com.tutorial.projectSoccer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findOneByUsername(String username);
}
