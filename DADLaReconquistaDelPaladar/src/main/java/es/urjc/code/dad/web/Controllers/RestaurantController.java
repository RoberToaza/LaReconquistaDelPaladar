package es.urjc.code.dad.web.Controllers;




import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
		
//		List<Product> listProductos = new ArrayList<>();
//		listProductos.add(p1);
//		listProductos.add(p2);
		
		Ticket t1 = ticketsRepository.save(new Ticket());
		
		
		SoldProduct sp1 = new SoldProduct("Aguacate", 3, 2, t1);
		
		soldProductsRepository.save(sp1);
		
		c1.getTickets().add(t1);
		clientsRepository.save(c1);
								
	}
	
	@GetMapping("/")
	public String showHome(Model model) {
		
		return "home_template";
	}

	@GetMapping("/{idClient}")
	public String showHomeWithClient(Model model, @PathVariable long idClient) {
		
		Client c = clientsRepository.findById(idClient);
		
		model.addAttribute("idClient", idClient);
		model.addAttribute(c);
		
		return "home_template";
	}
	
	@GetMapping("/contacto")
	public String showContacto() {

		return "contact_template";
	}
	
	@GetMapping("/contacto/{idClient}")
	public String showContactoWithClient(Model model, @PathVariable long idClient) {
		
		Client c = clientsRepository.findById(idClient);
		
		model.addAttribute("idClient", idClient);
		model.addAttribute(c);

		return "contact_template";
	}
	
//	@GetMapping("/cart/{idClient}")
//	public String showCarrito(Model model , @PathVariable long idClient) {
//		Client c = clientsRepository.findById(idClient);
//
//		List<Product> shoppingCart = c.getShoppingCar();
//		
//		if(shoppingCart.isEmpty()){
//			model.addAttribute("canBuy", false);
//		}else {
//			model.addAttribute("canBuy", true);
//		}
//		
//		model.addAttribute("idClient", idClient);
//		model.addAttribute(c);
//		model.addAttribute("cart", shoppingCart);
//		
//		return "cart_template";
//	}


}
