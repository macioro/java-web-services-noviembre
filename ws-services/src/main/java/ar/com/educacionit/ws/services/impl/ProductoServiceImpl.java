package ar.com.educacionit.ws.services.impl;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericExeption;
import ar.com.educacionit.ws.exceptions.ServiceException;
import ar.com.educacionit.ws.repository.ProductoRepository;
import ar.com.educacionit.ws.repository.impl.ProductoRepositoryHibImpl;
import ar.com.educacionit.ws.services.ProductoService;

public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;
	
	public ProductoServiceImpl() {
		this.productoRepository = new ProductoRepositoryHibImpl();  
	}
	
	public Producto obtenerProducto(Long id) {
		return this.productoRepository.getByID(id);
	}

	public List<Producto> findProductos() {
		return this.productoRepository.findAll();
	}

	public Producto nuevoProducto(Producto productoACrear) throws ServiceException {
		try {
			return this.productoRepository.insert(productoACrear);
		} catch (DuplicatedException e) {
			throw new ServiceException("Producto duplicado" + e.getMessage(), e);
		} catch (GenericExeption e) {
			throw new ServiceException("No se ha podido crear el producto");
		}
	}

	public Producto getProductoById(Long id) throws ServiceException {
		
		try {
			return this.productoRepository.getByID(id);
		} catch (GenericExeption e) {
			//LOG4J
			//SLF4
			//LOGBACK
			throw new ServiceException(e.getMessage());
		}
	}
	
	
}