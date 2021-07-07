package es.urjc.code.dad.web.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.repository.ClientRepository;
import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
public class TicketController {
	
	@Autowired
	private TicketRepository ticketsRepository;
	
	@Autowired
	private ClientRepository clientsRepository;

	@RequestMapping("/tickets")
	public String showTickets(Model model, HttpServletRequest request) {
		
		Principal currentUser = request.getUserPrincipal();
		
		Client currentClient = clientsRepository.findByFirstName(currentUser.getName());
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		
		model.addAttribute("token", token.getToken());
		
		model.addAttribute("tickets", ticketsRepository.findByClient(currentClient));
		
		model.addAttribute("client", currentClient);
		
		return "tickets_template";
	}
}
