package com.bdb.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdb.msvc.cursos.clients.UsuarioClienteRest;
import com.bdb.msvc.cursos.models.models.Usuario;
import com.bdb.msvc.cursos.models.models.entity.Curso;
import com.bdb.msvc.cursos.models.models.entity.CursoUsuario;
import com.bdb.msvc.cursos.repositories.CursosRepository;

@Service
public class CursoServiceImpl implements CursosService {

	@Autowired
	private CursosRepository repository;

	@Autowired
	private UsuarioClienteRest client;

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

	@Override
	public Optional<Curso> pornombre(String nombre) {

		return repository.findByNombre(nombre);
	}

	@Override
	public boolean existeporNombre(String nombre) {

		return repository.existsByNombre(nombre);
	}

	@Override
	public Optional<Usuario> asignarUsuario(Usuario usaurio, Long cursoId) {

		Optional<Curso> cursoO = repository.findById(cursoId);

		if (cursoO.isPresent()) {

			Usuario usuarioMsvc = client.detalle(usaurio.getId());

			Curso curso = cursoO.get();
			CursoUsuario cursoUsuario = new CursoUsuario();
			cursoUsuario.setUsuarioId(usuarioMsvc.getId());
			curso.addCursoUsuario(cursoUsuario);
			repository.save(curso);
			return Optional.of(usuarioMsvc);
		}

		return Optional.empty();
	}

	@Override
	public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {

		return Optional.empty();
	}

	@Override
	public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {

		return Optional.empty();
	}

}
