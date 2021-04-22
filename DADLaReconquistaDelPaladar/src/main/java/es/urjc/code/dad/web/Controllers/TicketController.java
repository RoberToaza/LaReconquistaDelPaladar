package es.urjc.code.dad.web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
public class TicketController {
	
	@Autowired
	private TicketRepository ticketsRepository;

	@RequestMapping("/tickets")
	public String showTickets(Model model) {
		model.addAttribute("tickets", ticketsRepository.findAll());
		
		return "tickets_template";
	}
}
