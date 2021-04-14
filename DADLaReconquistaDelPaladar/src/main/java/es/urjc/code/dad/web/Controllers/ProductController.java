package es.urjc.code.dad.web.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.dad.web.model.Category;
import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Product;
import es.urjc.code.dad.web.model.Ticket;
import es.urjc.code.dad.web.repository.CategoryRepository;
import es.urjc.code.dad.web.repository.ClientRepository;
import es.urjc.code.dad.web.repository.ProductRepository;
import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productsRepository;
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@Autowired 
	private TicketRepository ticketsRepository;
	
	@Autowired 
	private CategoryRepository categoriesRepository;
	
	@GetMapping("/menu")
	public String showMenu(Model model) {
		
		List<Product> menu = new ArrayList<>(productsRepository.findAll());
		
		List<Category> categories = new ArrayList<>(categoriesRepository.findAll());
		
		model.addAttribute("menu", menu);
		model.addAttribute("categories", categories);
		
		return"menu_template";
	}
	
//	@GetMapping("/menu/{idClient}")
//	public String showMenu(Model model, @PathVariable long idClient) {
//		Client c = clientsRepository.findById(idClient);
//		List<Product> menu = new ArrayList<>(productsRepository.findAll());
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
	
//	@PostMapping("/addToCart/{idClient}/{name}")
//	public String addProduct(Model model, @PathVariable long idClient, @PathVariable String name ) {
//		Client c = clientsRepository.findById(idClient);
//		Product p = productsRepository.findByName(name);
//	
//		
//		c.getShoppingCar().add(p);
//		
//		clientsRepository.save(c);
//		
//		long id = c.getId();
//		
//		List<Product> shoppingCart = c.getShoppingCar();
//		
//		model.addAttribute("canBuy", true);
//		model.addAttribute("cart",shoppingCart);
//		model.addAttribute("client", c);
//		model.addAttribute("idClient", id);
//		
//		return"cart_template";
//	}
	
//	@PostMapping("/delete/{idClient}/{name}")
//	public String deleteProduct(Model model, @PathVariable long idClient, @PathVariable String name ) {
//		Client c = clientsRepository.findById(idClient);
//		Product p = productsRepository.findByName(name);
//		
//		c.getShoppingCar().remove(p);
//		
//		clientsRepository.save(c);
//		
//		long id = c.getId();
//		
//		List<Product> shoppingCart = c.getShoppingCar();
//		
//		if(!c.getShoppingCar().isEmpty())
//			model.addAttribute("canBuy", true);
//		model.addAttribute("cart",shoppingCart);
//		model.addAttribute("client", c);
//		model.addAttribute("idClient", id);
//		
//		return"cart_template";
//	}
//	
////	@PostMapping("/buy/{idClient}")
////	public String buyCart(Model model, @PathVariable long idClient) {
////		Client c = clientsRepository.findById(idClient);
////		List<Product> list = c.getShoppingCar();
////		Ticket ticketCart = new Ticket(c.getShoppingCar());
////		
////		c.getTickets().add(ticketCart);
////		
////		clientsRepository.save(c);
////		
////		long id = c.getId();
////		
////		model.addAttribute("client", c);
////		model.addAttribute("idClient", id);
////		
////		return "info_template";
////		
////	}
//	
//	@PostMapping("/buy/{idClient}")
//	public String buyCart(Model model, @PathVariable long idClient) {
//		Client c = clientsRepository.findById(idClient);
//		
//		if(c.getShoppingCar().size()>0) {
//			List<Product> aux = new ArrayList<>(c.getShoppingCar());
//			
//			Ticket ticketCart = new Ticket(aux);
//			ticketsRepository.save(ticketCart);
//			
//			c.setShoppingCar(new ArrayList<Product>());
//			
//			c.getTickets().add(ticketCart);
//			
//			clientsRepository.save(c);
//		}
//		
//		long id = c.getId();
//		
//		model.addAttribute("client", c);
//		model.addAttribute("idClient", id);
//		
//		return "cart_template";
//		
//	}
	

}
