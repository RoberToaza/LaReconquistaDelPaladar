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
import es.urjc.code.dad.web.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productsRepository;
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@Autowired 
	private CategoryRepository categoriesRepository;
	
	@GetMapping("/menu")
	public String showMenu(Model model, HttpServletRequest request) {
		
		List<Product> menu = new ArrayList<>(productsRepository.findAll());
		
		List<Category> categories = new ArrayList<>(categoriesRepository.findAll());
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		model.addAttribute("menu", menu);
		model.addAttribute("categories", categories);
		
		try {
			Client currentClient = clientsRepository.findByFirstName(request.getUserPrincipal().getName());
			model.addAttribute("client", currentClient);
		} catch(Exception e) {}
		
		return"menu_template";
	}
	

}
