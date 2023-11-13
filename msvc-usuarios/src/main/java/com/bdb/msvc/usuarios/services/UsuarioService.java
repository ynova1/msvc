package com.bdb.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.bdb.msvc.usuarios.entity.Usuario;

public interface UsuarioService {
	List<Usuario> listar();

	Optional<Usuario> porId(Long id);

	Optional<Usuario> porEmail(String email);

	Usuario guardar(Usuario usuario);

	boolean existePorEmail(String email);

	void eliminar(Long id);
}
