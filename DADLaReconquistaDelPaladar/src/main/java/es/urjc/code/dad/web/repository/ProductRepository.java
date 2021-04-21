package es.urjc.code.dad.web.repository;

import org.springframework.data.repository.CrudRepository;

import es.urjc.code.dad.web.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findAll();
	
	Product findByName(String name);

}
