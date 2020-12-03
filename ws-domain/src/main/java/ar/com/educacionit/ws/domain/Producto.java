package ar.com.educacionit.ws.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;

	@Column(name = "codigo", nullable = false, unique = true, length = 50)
	private String codigo;

	@Column(name = "precio", nullable = false)
	private float precio;

	public Producto() {
	}

	public Producto(Long id, String titulo, String codigo, float precio) {
		this.id = id;
		this.titulo = titulo;
		this.codigo = codigo;
		this.precio = precio;
	}

	public Producto(String titulo, String codigo, float precio) {
		this.titulo = titulo;
		this.codigo = codigo;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
