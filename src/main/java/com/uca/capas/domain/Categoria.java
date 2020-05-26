package com.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(schema = "public", name = "cat_categoria")
public class Categoria {
	
	@Id
	@Column(name = "c_categoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	
	@Column(name = "s_categoria")
	@NotEmpty(message = "El campo no puede estar vacío")
	@Size(message = "El campo sobrepasa la cantidad de 50 caracteres", max = 50)
	private String categoria;
	
	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	private List<Libro> libro;

	public Categoria() {
	
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	
}
