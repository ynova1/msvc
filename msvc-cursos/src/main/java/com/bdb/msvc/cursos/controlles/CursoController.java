package com.bdb.msvc.cursos.controlles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.msvc.cursos.models.models.entity.Curso;
import com.bdb.msvc.cursos.services.CursosService;

import jakarta.validation.Valid;

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
	public ResponseEntity<?> guardar(@Valid @RequestBody Curso curso, BindingResult result) {
		if (result.hasErrors()) {
			return validacion(result);
		}
		if (!curso.getNombre().isEmpty() && service.existeporNombre(curso.getNombre())) {
			return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje",
					"El curso con nombre " + curso.getNombre() + " ya existe en el sistema"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return validacion(result);
		}

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

	private ResponseEntity<?> validacion(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "el campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
