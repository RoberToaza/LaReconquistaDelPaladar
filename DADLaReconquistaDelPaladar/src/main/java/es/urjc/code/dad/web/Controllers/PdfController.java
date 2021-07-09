package es.urjc.code.dad.web.Controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Ticket;
import es.urjc.code.dad.web.repository.ClientRepository;
import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
public class PdfController {

	@Autowired
	private TicketRepository ticketsRepository;
	
	@Autowired
	private ClientRepository clientsRepository;
	
	@GetMapping("/makePdf/{id}")
	public ResponseEntity<byte[]> makePdf(Model model, @PathVariable String id, HttpServletRequest request){
		
		Principal c = request.getUserPrincipal();
		
		model.addAttribute("client", c.getName());
		
		Optional<Ticket> auxTicket = ticketsRepository.findById(Integer.parseInt(id));
		
		Client client = clientsRepository.findByFirstName(c.getName());
		Ticket ticket = auxTicket.get();
		if(client.getFisrtName() == ticket.getClient().getFisrtName()) {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://thishaproxy:8088/getPdf/" + id;
			ResponseEntity<byte[]> response = restTemplate.postForEntity(url, null, byte[].class);
			return response;
		}
		
		return null;
		
	}
}
