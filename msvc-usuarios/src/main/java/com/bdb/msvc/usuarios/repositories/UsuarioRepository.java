package com.bdb.msvc.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bdb.msvc.usuarios.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
