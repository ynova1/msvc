package com.bdb.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.bdb.msvc.cursos.models.models.Usuario;
import com.bdb.msvc.cursos.models.models.entity.Curso;

public interface CursosService {

	List<Curso> listar();

	Optional<Curso> porId(Long id);

	Optional<Curso> pornombre(String nombre);

	boolean existeporNombre(String nombre);

	Curso guardar(Curso curso);

	void eliminar(Long id);

	Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);

	Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

	Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}
