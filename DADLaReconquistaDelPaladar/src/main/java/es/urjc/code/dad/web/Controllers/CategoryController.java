package es.urjc.code.dad.web.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
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
	private CategoryRepository categoriesRepository;
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@GetMapping("/{name}")
	public String showByCategory(Model model, HttpServletRequest request, @PathVariable String name) {
		Category category =categoriesRepository.findByName(name);
		List<Product> menu = new ArrayList<>(category.getProducts());
		List<Category> categories = new ArrayList<>(categoriesRepository.findAll());
		
		model.addAttribute("categories", categories);
		
		
		model.addAttribute("menu", menu);
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		try {
			Client currentClient = clientsRepository.findByFirstName(request.getUserPrincipal().getName());
			model.addAttribute("client", currentClient);
		} catch(Exception e) {}
		
		return"menu_template";
	}

}
