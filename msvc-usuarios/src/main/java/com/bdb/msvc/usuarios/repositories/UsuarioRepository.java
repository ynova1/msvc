package com.bdb.msvc.usuarios.repositories;

import java.util.Optional;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bdb.msvc.usuarios.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	/*
	 * otra forma de construir le metodo pero usando query
	 * 
	 * @Query("select u from Usuario u where u.email=?1") 
	 * Optional<Usuario> findByEmail(String email);
	 */

	boolean existsByEmail(String email);

}
