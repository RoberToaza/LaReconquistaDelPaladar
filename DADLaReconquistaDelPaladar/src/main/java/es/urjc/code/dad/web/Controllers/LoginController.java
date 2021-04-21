package es.urjc.code.dad.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.repository.ClientRepository;

@Controller
public class LoginController {
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@RequestMapping("/loginPage")
	public String loginPage(Model model) {
		return "login_template";
	}
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam String username, @RequestParam String password) {
		Client c = clientsRepository.findByFirstNameAndPassword(username, password);
		
		if(c == null) {
			return("home_template");
		}
		long id = c.getId();
		
		model.addAttribute("client", c);
		model.addAttribute("idClient", id);

		return "home_template";
	}

}
