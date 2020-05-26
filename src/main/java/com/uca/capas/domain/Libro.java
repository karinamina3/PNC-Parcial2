package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(schema = "public", name = "cat_libro")
public class Libro {

    @Id
    @Column(name = "c_libro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @Column(name = "s_titulo")
    @NotEmpty(message = "El campo no puede estar vacío")
    @Size(message = "El campo sobrepasa la cantidad de 500 caracteres", max = 500)
    private String titulo;

    @Column(name = "s_autor")
    @NotEmpty(message = "El campo no puede estar vacío")
    @Size(message = "El campo sobrepasa la cantidad de 150 caracteres", max = 150)
    private String autor;

    @Column(name = "b_estado")
    private Boolean estado;

    @Column(name="s_isbn")
    @NotEmpty(message = "El campo no puede estar vacío")
    @Size(message = "El campo sobrepasa la cantidad de 10 caracteres", max = 10)
    private String isbn;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="f_ingreso")
    private Date fechaIngreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_categoria")
    private Categoria categoria;
    
    public Libro() {
    	
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
	// Delegate para el estado
	public String getEstadoDelegate(){
		if (this.estado == null) return "";
		else {
			if (this.estado) return "ACTIVO";
			else return "INACTIVO";
		}
	}
	
	// Delegate para fecha de ingreso
	public String getFechaDelegate() {
		if (this.fechaIngreso == null) return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			return sdf.format(this.fechaIngreso.getTime());
		}
	}
}
