package com.bdb.msvc.cursos.controlles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.msvc.cursos.models.entity.Curso;
import com.bdb.msvc.cursos.services.CursosService;

@RestController
public class CursoController {

	@Autowired
	private CursosService service;

	@GetMapping
	public List<Curso> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id) {

		Optional<Curso> cursoO = service.porId(id);
		if (cursoO.isPresent()) {

			return ResponseEntity.ok().body(cursoO.get());
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Curso curso) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {

		Optional<Curso> cursoO = service.porId(id);
		if (cursoO.isPresent()) {

			Curso cursoDB = cursoO.get();

			cursoDB.setNombre(curso.getNombre());

			return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoDB));

		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {

		Optional<Curso> cursoO = service.porId(id);
		if (cursoO.isPresent()) {
			service.eliminar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

}
