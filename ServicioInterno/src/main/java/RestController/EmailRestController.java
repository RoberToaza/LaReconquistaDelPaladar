package RestController;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import entities.Ticket;
import es.urjc.code.dad.web.repository.TicketRepository;

@RestController
public class EmailRestController {
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private TicketRepository ticketsRepository;
	
	@PostMapping("/sendEmail/{id}")
	public boolean sendEmail(@RequestBody String email, @PathVariable String id)throws DocumentException, MessagingException, IOException{
		
		MimeMessage message = emailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		messageHelper.setTo(email);
		
		messageHelper.setFrom("LaReconquista", "La Reconquista");
		messageHelper.setSubject("Ticker de la compra realizada");
		
		int idNum = Integer.parseInt(id);
		
		Optional<Ticket> ticket = ticketsRepository.findById((long) idNum);
		
		String cName = ticket.get().getClient().getFisrtName();
		String num = Integer.toString(ticket.get().getId());
		
	}

}
