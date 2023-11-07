package com.bdb.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.bdb.msvc.cursos.models.entity.Curso;

public interface CursosService {
	
	List<Curso> listar();
	Optional<Curso> porId(Long id);
	Curso guardar(Curso curso);
	void eliminar(Long id);

}
