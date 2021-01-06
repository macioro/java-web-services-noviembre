package ar.com.educacionit.ws.rest.resources;

public enum ProductoRestWSErrorEnum {

	ID_REQUERIDO(1, "Debe indicar el id"),
	PRECIO(2,"Debe indicar el precio"),
	CODIGO(3, "Debe indicar el código"),
	TITULO(4, "Debe indicar el título"),
	TIPO_PRODUCTO(5, "Debe indicar el tipo de producto"), 
	ID_INEXISTENTE(6, "Producto Inexistente");
	
	private Integer id;
	private String msj;
	
	private ProductoRestWSErrorEnum(Integer id, String msj) {
		this.id = id;
		this.msj = msj;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getMsj() {
		return this.msj;
	}
}