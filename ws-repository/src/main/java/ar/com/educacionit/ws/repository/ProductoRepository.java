package ar.com.educacionit.ws.repository;

import java.util.List;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericExeption;

public interface ProductoRepository {

	public Producto getByID(Long id) throws GenericExeption;

	public List<Producto> findAll();

	public Producto insert(Producto productoACrear) throws DuplicatedException, GenericExeption;

	public Producto deleteProducto(Long id) throws GenericExeption;
}