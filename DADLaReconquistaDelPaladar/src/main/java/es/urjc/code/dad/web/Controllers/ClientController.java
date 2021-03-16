package es.urjc.code.dad.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Product;
import es.urjc.code.dad.web.model.Ticket;
import es.urjc.code.dad.web.repository.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@PostMapping("/login")
	public String logIn(Model model, @RequestParam String firstName, @RequestParam String password) {
		Client c = clientsRepository.findByFirstNameAndPassword(firstName, password);
		
		if(c == null) {
			return("home_template");
		}
		long id = c.getId();
		
		model.addAttribute("client", c);
		model.addAttribute("idClient", id);

		return "home_template";
	}
	
	@GetMapping("/signup")
	public String newClient(Model model) {
		
		return "signup_template";
	}
	
	
	@PostMapping("/new")
	public String createClient(Model model, Client c){
		Client newC = new Client(c);
		
		clientsRepository.save(newC);
		
		model.addAttribute("client", newC);
		
		
		long id = newC.getId();
		
		model.addAttribute("idClient", id);
		
		return "home_template";
	}
	
	@GetMapping("/info/{idClient}")
	public String newClient(Model model, @PathVariable long idClient) {
		Client c = clientsRepository.findById(idClient);
		List<Product> shoppingCart = c.getShoppingCar();
		List<Ticket> tickets = c.getTickets();
		
		long id = c.getId();
		
		model.addAttribute("tickets", tickets);
		model.addAttribute("client", c);
		model.addAttribute("idClient", id);
		model.addAttribute("cart",shoppingCart);
		
		return "info_template";
	}

}
