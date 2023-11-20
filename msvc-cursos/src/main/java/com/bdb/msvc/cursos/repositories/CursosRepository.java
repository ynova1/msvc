package com.bdb.msvc.cursos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bdb.msvc.cursos.models.models.entity.Curso;

public interface CursosRepository extends CrudRepository<Curso, Long> {

	Optional<Curso> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
