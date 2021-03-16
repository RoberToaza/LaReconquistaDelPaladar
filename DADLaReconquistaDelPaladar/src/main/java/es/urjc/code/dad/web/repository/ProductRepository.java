package es.urjc.code.dad.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAll();
	
	Product findByName(String name);

}
