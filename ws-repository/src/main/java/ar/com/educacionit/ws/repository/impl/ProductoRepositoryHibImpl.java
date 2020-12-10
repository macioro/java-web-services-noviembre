package ar.com.educacionit.ws.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import ar.com.educacionit.ws.domain.Producto;
import ar.com.educacionit.ws.exceptions.DuplicatedException;
import ar.com.educacionit.ws.exceptions.GenericExeption;
import ar.com.educacionit.ws.repository.ProductoRepository;
import ar.com.educacionit.ws.repository.hibernate.HibernateBaseRepository;

public class ProductoRepositoryHibImpl extends HibernateBaseRepository implements ProductoRepository {

	public ProductoRepositoryHibImpl() {
		super();
	}
	
	public Producto getByID(Long id) throws GenericExeption {
				
		Session session = factory.getCurrentSession();

		Producto producto = null;
		
		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + Producto.class.getName() + " e where e.id=:id ";

			// Create Query object.
			Query<Producto> query = session.createQuery(sql);

			query.setParameter("id", id);

			// Execute query.
			Optional<Producto> employees = query.uniqueResultOptional();

			if(employees.isPresent()) {
				producto = employees.get();
				
				System.out.println(producto);
			}
			
			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericExeption(e.getCause().getMessage(), e);
		}
		
		return producto;
	}

	public List<Producto> findAll() {
		
		List<Producto> productos = new ArrayList<Producto>();
				
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		//hql
		String sql = "Select p from " + Producto.class.getName() + " p";
		
		Query<Producto> query = session.createQuery(sql);
		
		productos = query.getResultList();
	
		session.getTransaction().commit();
		
		return productos;
	}

	public Producto insert(Producto productoACrear) throws DuplicatedException, GenericExeption {
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		try {
			session.saveOrUpdate(productoACrear);
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new DuplicatedException(e.getCause().getMessage(), e);
		}catch (Exception e) {
			session.getTransaction().rollback();
			throw new GenericExeption(e.getMessage(), e);
		} finally {
			session.close();
		}
				
		return productoACrear;
	}

}