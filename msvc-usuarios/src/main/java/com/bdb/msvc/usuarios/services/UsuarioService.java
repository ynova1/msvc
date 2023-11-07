package com.bdb.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.bdb.msvc.usuarios.entity.Usuario;


public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}

