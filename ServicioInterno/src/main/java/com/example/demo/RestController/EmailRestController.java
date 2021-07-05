package com.example.demo.RestController;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Ticket;
import com.example.demo.repository.TicketRepository;
import com.itextpdf.text.DocumentException;

@RestController
public class EmailRestController {
	
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private TicketRepository ticketsRepository;
	
	@PostMapping("/sendEmail/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean sendEmail(@RequestBody String email, @PathVariable int id)throws DocumentException, MessagingException, IOException{
		
		System.out.println("Soy el servicio interno\n");
		
		MimeMessage message = emailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		messageHelper.setTo(email);
		
		messageHelper.setFrom("LaReconquista", "La Reconquista");
		messageHelper.setSubject("Ticker de la compra realizada");
		
		int idNum = id;
		
		Optional<Ticket> ticket = ticketsRepository.findById(idNum);
		
		String cName = ticket.get().getClient().getFisrtName();
		String num = Integer.toString(ticket.get().getId());
		
		messageHelper.setText("Este correo fué generado automáticamente, No responda a este correo \n\n" + cName +", Su pedido con número :"+ num + "ha sido procesado con exito. \n\n Ya tu sabe\n\n");
		
		emailSender.send(message);
		
		return true;
		
	}

}
