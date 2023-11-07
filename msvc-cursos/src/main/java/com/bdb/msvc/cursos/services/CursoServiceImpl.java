package com.bdb.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdb.msvc.cursos.models.entity.Curso;
import com.bdb.msvc.cursos.repositories.CursosRepository;

@Service
public class CursoServiceImpl implements CursosService {

	@Autowired
	private CursosRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> listar() {

		return (List<Curso>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> porId(Long id) {

		return repository.findById(id);
	}

	@Override
	@Transactional
	public Curso guardar(Curso curso) {

		return repository.save(curso);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		repository.deleteById(id);

	}

}
