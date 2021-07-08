package es.urjc.code.dad.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	List<Category> findAll();
	
	Category findByName(String name);

}
