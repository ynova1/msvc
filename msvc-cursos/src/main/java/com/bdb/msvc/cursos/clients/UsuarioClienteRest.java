package com.bdb.msvc.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bdb.msvc.cursos.models.models.Usuario;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClienteRest {

	@GetMapping("/{id}")
	public Usuario detalle(@PathVariable Long id);

	@PostMapping
	public Usuario crear(@RequestBody Usuario usaurio);

}
