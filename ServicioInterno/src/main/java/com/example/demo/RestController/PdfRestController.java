package com.example.demo.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Ticket;
import com.example.demo.repository.TicketRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfRestController {
	
	@Autowired
	private TicketRepository ticketsRepository;
	
	@PostMapping("/getPdf/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<byte[]> createPdf(@PathVariable(required = true) String id) throws DocumentException, IOException{
		
		System.out.println("Dentro de servicio interno");
		
		Optional<Ticket> ticket = ticketsRepository.findById(Integer.parseInt(id));
		
		Ticket t = ticket.get();
		String name = t.getClient().getFisrtName();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Ticket"+id+".pdf"));
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16,BaseColor.BLACK);
		Paragraph p = new Paragraph("Ticket "+id+" de "+ name,font);
		
		Paragraph blank = new Paragraph("", font);
		Paragraph total = new Paragraph("Precio total: "+ t.getTotal()+" $", font);
		
		document.add(p);
		document.add(blank);
		document.add(total);
		
		document.close();
		
		byte[] contents = Files.readAllBytes(Paths.get("Ticket"+id+".pdf"));
		
		File pdfToDelete = new File("Ticket"+id+".pdf");
		pdfToDelete.delete();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		
		String filename = "Ticket"+id+".pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		
		return response;
		
	}

}
