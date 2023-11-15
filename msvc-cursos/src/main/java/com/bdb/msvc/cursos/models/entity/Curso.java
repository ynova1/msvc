package com.bdb.msvc.cursos.models.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank()
	private String nombre;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CursoUsuario> cursoUsuarios;

	public Curso() {
		cursoUsuarios = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addCursoUsuario(CursoUsuario cursoUsuario) {

		cursoUsuarios.add(cursoUsuario);
	}

	public void removeCursoUsuario(CursoUsuario cursoUsuario) {
		cursoUsuarios.remove(cursoUsuario);
	}

	public List<CursoUsuario> getCursoUsaurio() {
		return cursoUsuarios;
	}

	public void setCursoUsaurio(List<CursoUsuario> cursoUsaurio) {
		this.cursoUsuarios = cursoUsaurio;
	}

}
