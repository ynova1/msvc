package com.bdb.msvc.usuarios.controlles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bdb.msvc.usuarios.entity.Usuario;
import com.bdb.msvc.usuarios.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<Usuario> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = service.porId(id);
		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

		if (result.hasErrors()) {
			return validacion(result);
		}

		if (!usuario.getEmail().isEmpty() && service.existePorEmail(usuario.getEmail())) {
			return ResponseEntity.badRequest().body(
					Collections.singletonMap("Mensaje", "ya existe un usuario con el email " + usuario.getEmail()));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, @PathVariable Long id, BindingResult result) {

		if (result.hasErrors()) {
			return validacion(result);
		}

		Optional<Usuario> o = service.porId(id);

		if (o.isPresent()) {

			Usuario usuarioDb = o.get();

			if (!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail())
					&& service.porEmail(usuario.getEmail()).isPresent()) {
				return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje",
						"ya esta registrado el email " + usuario.getEmail() + "en otro usuario"));
			}

			usuarioDb.setNombre(usuario.getNombre());
			usuarioDb.setEmail(usuario.getEmail());
			usuarioDb.setPassword(usuario.getPassword());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Optional<Usuario> o = service.porId(id);
		if (o.isPresent()) {
			service.eliminar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validacion(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "el campo" + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
}
