package es.urjc.code.dad.web.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.dad.web.model.Category;
import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Product;
import es.urjc.code.dad.web.repository.CategoryRepository;
import es.urjc.code.dad.web.repository.ClientRepository;


@Controller
@RequestMapping("/category")
public class CategoryController {

	
	@Autowired
	private ClientRepository clientsRepository;

	
	@Autowired 
	private CategoryRepository categoriesRepository;
	
	@GetMapping("/{name}")
	public String showByCategory(Model model, @PathVariable String name) {
		Category category =categoriesRepository.findByName(name);
		List<Product> menu = new ArrayList<>(category.getProducts());
		List<Category> categories = new ArrayList<>(categoriesRepository.findAll());
		
		model.addAttribute("categories", categories);
		
		
		model.addAttribute("menu", menu);
		
		return"menu_template";
	}
	
//	@GetMapping("/{name}/{idClient}")
//	public String showByCategory(Model model, @PathVariable String name, @PathVariable long idClient) {
//		Client c = clientsRepository.findById(idClient);
//		Category category =categoriesRepository.findByName(name);
//		List<Product> menu = new ArrayList<>(category.getProducts());
//		List<Category> categories = new ArrayList<>(categoriesRepository.findAll());
//		
//		long id = c.getId();
//		
//		model.addAttribute("client", c);
//		model.addAttribute("idClient", id);
//		model.addAttribute("categories", categories);
//		
//		
//		model.addAttribute("menu", menu);
//		
//		return"menu_template";
//	}

}
