package es.urjc.code.dad.web.Controllers;




import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.urjc.code.dad.web.model.Category;
import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Product;
import es.urjc.code.dad.web.model.SoldProduct;
import es.urjc.code.dad.web.model.Ticket;
import es.urjc.code.dad.web.repository.CategoryRepository;
import es.urjc.code.dad.web.repository.ClientRepository;
import es.urjc.code.dad.web.repository.ProductRepository;
import es.urjc.code.dad.web.repository.SoldProductRepository;
import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
public class RestaurantController {

	@Autowired
	private ClientRepository clientsRepository;

	@Autowired
	private TicketRepository ticketsRepository;

	@Autowired
	private CategoryRepository categoriesRepository;

	@Autowired
	private ProductRepository productsRepository;
	
	@Autowired
	private SoldProductRepository soldProductsRepository;

	@PostConstruct
	public void init() {
		
		Product p1 = new Product("Empanada de verde", 3, 10, "Ecuador");
		Product p2 = new Product("Cacao", 5, 3, "Ecuador");
		Product p3 = new Product("Saltena", 2, 10, "Bolivia");
		
		productsRepository.save(p1);
		productsRepository.save(p2);
		productsRepository.save(p3);

		
		Category cat1 = new Category("Ecuador");
		cat1.getProducts().add(p1);
		cat1.getProducts().add(p2);
		
		Category cat2 = new Category("Bolivia");
		cat2.getProducts().add(p3);
		
		categoriesRepository.save(cat1);
		categoriesRepository.save(cat2);

		Client c1 = new Client("Roberto", "Toaza", 23, "09099271B", "robertoaza.jr@gmail.com", 999999999,
				"Alcalá de Henares", "password");
		
		clientsRepository.save(c1);
		
		
		Ticket t1 = ticketsRepository.save(new Ticket());
		
		
		SoldProduct sp1 = new SoldProduct("Aguacate", 3, 2, t1);
		
		soldProductsRepository.save(sp1);
		
		clientsRepository.save(c1);
								
	}
	
	@GetMapping("/")
	public String showHome(Model model, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		Principal currentUser = request.getUserPrincipal();
		try {
			Client currentClient = clientsRepository.findByFirstName(currentUser.getName());
			model.addAttribute("client", currentClient);
		} catch(Exception e) {}
		
		
		return "home_template";
	}

//	@GetMapping("/{idClient}")
//	public String showHomeWithClient(Model model, @PathVariable int idClient) {
//		
//		Client c = clientsRepository.findById(idClient);
//		
//		model.addAttribute("idClient", idClient);
//		model.addAttribute(c);
//		
//		return "home_template";
//	}
	
	@GetMapping("/contacto")
	public String showContacto(Model model, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		model.addAttribute("token", token.getToken());
		
		try {
			Client currentClient = clientsRepository.findByFirstName(request.getUserPrincipal().getName());
			model.addAttribute("client", currentClient);
		} catch(Exception e) {}

		return "contact_template";
	}
	
//	@GetMapping("/contacto/{idClient}")
//	public String showContactoWithClient(Model model, @PathVariable int idClient) {
//		
//		Client c = clientsRepository.findById(idClient);
//		
//		model.addAttribute("idClient", idClient);
//		model.addAttribute(c);
//
//		return "contact_template";
//	}


}
