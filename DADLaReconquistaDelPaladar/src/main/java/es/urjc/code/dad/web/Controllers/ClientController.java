package es.urjc.code.dad.web.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.repository.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@PostMapping("/login2")
	public String logIn(Model model, @RequestParam String firstName, @RequestParam String password) {
		Client c = clientsRepository.findByFirstNameAndPasswordHash(firstName, password);
		
		if(c == null) {
			return("home_template");
		}
		long id = c.getId();
		
		model.addAttribute("client", c);
		model.addAttribute("idClient", id);

		return "home_template";
	}
	
	@GetMapping("/signup")
	public String newClient(Model model, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		model.addAttribute("token", token.getToken());
		return "signup_template";
	}
	
	
	@PostMapping("/new")
	public String createClient(Model model, Client c){
		Client newC = new Client(c);
		
		clientsRepository.save(newC);
		
		model.addAttribute("client", newC);
		
		
		int id = newC.getId();
		
		model.addAttribute("idClient", id);
		
		return "redirect:/login";
	}
	
	@RequestMapping("/profile")
	public String showTickets(Model model, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		model.addAttribute("token", token.getToken());
		
		Principal currentUser = request.getUserPrincipal();
		
		Client currentClient = clientsRepository.findByFirstName(currentUser.getName());
		
		model.addAttribute("client", currentClient);
		
		return "profile_template";
	}

}
