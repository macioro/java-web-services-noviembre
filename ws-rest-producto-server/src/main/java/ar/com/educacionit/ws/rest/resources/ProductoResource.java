package ar.com.educacionit.ws.rest.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.rest.dto.ProductoRequestDTO;
import ar.com.educacionit.ws.services.ProductoService;
import ar.com.educacionit.ws.services.impl.ProductoServiceImpl;

@Path("productos")
public class ProductoResource {

	private ProductoService ps = new ProductoServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProductos() {
		List<Producto> productos = ps.findProductos();
		return Response.ok(productos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProducto(ProductoRequestDTO productoRequest) {
		
		//reto agregar valiciones!
		
		//tips usar mapa codigo=valor
		
		//bad_request!
		
		Producto productoACrear = new Producto(productoRequest.getTitulo(),productoRequest.getCodigo(),productoRequest.getPrecio(), productoRequest.getTipoProducto());
		
		try {
			Producto productoCreado = this.ps.nuevoProducto(productoACrear);
			return Response.status(Status.CREATED).entity(productoCreado).build();
		} catch (ServiceException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(contruirRespuestaErrores(se))
				.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getProducto(@PathParam("id") Long id ) {
		
		try {
			Producto producto = this.ps.getProductoById(id);
			
			if(producto != null) {
				return Response.ok(producto).build();
			}else {
				//agreguen algo de codigo para mostrar un msj mas lindo!
				return Response.status(Status.NOT_FOUND).entity("Producto no encontrado").build();		
			}
		}catch (ServiceException se) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(contruirRespuestaErrores(se))
				.build();
		}
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteProducto(
			@PathParam("id") Long id
			) {
		
		//validaciones
		if(id == null) {
			Map<Integer, String> error = contruirErroresValidacion(ProductoRestWSErrorEnum.ID_REQUERIDO);
			return Response.status(Status.BAD_REQUEST).entity(error).build();
		}
		
		try {
			Producto productoEliminado = this.ps.eliminarProducto(id);
			
			return Response.ok(productoEliminado).build();
			
		} catch (ServiceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(contruirRespuestaErrores(e))
					.build();
		}
		
	}
	
	public Map<String, String> contruirRespuestaErrores(ServiceException se) {
		Map<String, String> errores = new HashMap<String, String>();
		if(se.getCause() != null) {
			errores.put("error", se.getCause().getMessage());
		} else {
			errores.put("error", se.getMessage());
		}
		return errores;		
	}
	
	public Map<Integer, String> contruirErroresValidacion(ProductoRestWSErrorEnum enumError) {
		Map<Integer,String> error = new HashMap<Integer, String>();		
		error.put(enumError.getId(), enumError.getMsj());
		return error;
	}
	
}