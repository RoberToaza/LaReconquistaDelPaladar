package es.urjc.code.dad.web.Controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.EmailBody;
import es.urjc.code.dad.web.model.Product;
import es.urjc.code.dad.web.model.ProductInCart;
import es.urjc.code.dad.web.model.SoldProduct;
import es.urjc.code.dad.web.model.Ticket;
import es.urjc.code.dad.web.repository.ClientRepository;
import es.urjc.code.dad.web.repository.ProductRepository;
import es.urjc.code.dad.web.repository.SoldProductRepository;
import es.urjc.code.dad.web.repository.TicketRepository;

@Controller
public class CartController {
	
	@Autowired
	private ProductRepository productsRepository;
	
	@Autowired 
	private TicketRepository ticketsRepository;
	
	@Autowired
	private SoldProductRepository soldProductsRepository;
	
	@Autowired
	private ClientRepository clientsRepository;

	@GetMapping("/cart")
	public String showMenu(Model model, HttpServletRequest request) {		
		model.addAttribute("product", new Product());
		double total = 0;
		ArrayList<ProductInCart> cart = this.getCart(request);
		
		for(ProductInCart p : cart)
			total += p.getTotal();
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		model.addAttribute("cart", cart);
		model.addAttribute("total", total);
		return "cart2_template";
	}
	
	@PostMapping("/addToCart/{name}")
	public String addToCart(Model model, @PathVariable String name, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ArrayList<ProductInCart> cart = this.getCart(request);
		Product product_aux = productsRepository.findByName(name);
		
		boolean found = false;
		for(ProductInCart p : cart) {
			if(p.getName().equals(product_aux.getName())){
				p.addAmount();
				found = true;
				break;
			}
		}
		if(!found) {
			cart.add(new ProductInCart(product_aux.getName(), product_aux.getPrice(), product_aux.getStock(),product_aux.getCountry(),1));
			
		}
		
		this.saveCart(cart, request);
		
		return "redirect:/cart";
		
	}
	
	@PostMapping("/deleteFromCart/{index}")
	public String deleteFromCart(@PathVariable int index, HttpServletRequest request) {
		ArrayList<ProductInCart> cart = this.getCart(request);
		if(cart==null) {
			System.out.print("vacío");
		}
		System.out.print("No Vacío");
		if(cart != null && cart.size() > 0 && cart.get(index-1)!= null) {
			cart.remove(index-1);
			this.saveCart(cart, request);
		}
		return "redirect:/cart";
	}
	
	
	
	@PostMapping("/buyCart")
	public String buyProducts(HttpServletRequest request) {
		
		Principal currentUser = request.getUserPrincipal();
		Client currentClient;
		
		try {
			currentClient = clientsRepository.findByFirstName(currentUser.getName());
		} catch (Exception e) {
			return "redirect:/dbError";
		}
		
		ArrayList<ProductInCart> cart = this.getCart(request);
		if((cart == null) || cart.size() <= 0){
			return "redirect:/";
		}
		
		List<SoldProduct> auxL = new ArrayList<>();
		Ticket t = ticketsRepository.save(new Ticket());
		
		for(ProductInCart p: cart) {
			Product pDB = productsRepository.findByName(p.getName());
			
			pDB.subtractStock(p.getAmount());
			
			productsRepository.save(pDB);
			
			SoldProduct soldP = new SoldProduct(p.getName(), p.getPrice(), p.getAmount(), t);
			
			soldProductsRepository.save(soldP);
			auxL.add(soldP);
		}
		
		t.setProducts(auxL);
		t.setClient(currentClient);
				
		ticketsRepository.save(t);
		
		this.emptyCart(request);
		
		System.out.println("Exito");
		
		return "redirect:/";
		
	}
	
	@PostMapping("/buyCartAndSendEmail")
	public String buyProductsAndSendEmail(HttpServletRequest request) {
		
		Principal currentUser = request.getUserPrincipal();
		Client currentClient= clientsRepository.findByFirstName(currentUser.getName());

		
		ArrayList<ProductInCart> cart = this.getCart(request);
		if((cart == null) || cart.size() <= 0){
			return "redirect:/";
		}
		
		List<SoldProduct> auxL = new ArrayList<>();
		Ticket t = ticketsRepository.save(new Ticket());
		
		for(ProductInCart p: cart) {
			Product pDB = productsRepository.findByName(p.getName());
			
			pDB.subtractStock(p.getAmount());
			
			productsRepository.save(pDB);
			
			SoldProduct soldP = new SoldProduct(p.getName(), p.getPrice(), p.getAmount(), t);
			
			soldProductsRepository.save(soldP);
			auxL.add(soldP);
		}
		
		t.setProducts(auxL);
		t.setClient(currentClient);
				
		ticketsRepository.save(t);
		
		this.emptyCart(request);
		
//		String email = "robertoaza@hotmail.com";
//		RestTemplate restTemplate = new RestTemplate();
//		String url = "http://127.0.0.1:8080/sendEmail/"+ t.getId();
//		ResponseEntity<Boolean> response = restTemplate.postForEntity(url,email, Boolean.class);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<EmailBody> mailBody = new HttpEntity<>(new EmailBody("robertoaza@hotmail.com","A sido un exito","Factura Generada"));
		ResponseEntity<String> enviar = restTemplate.postForEntity("http://localhost:8080/email/send", mailBody, String.class);
		
		return "redirect:/tickets";
		
	}
	
	@GetMapping("/sendEmail")
	public String pruebita(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<EmailBody> mailBody = new HttpEntity<>(new EmailBody("robertoaza@hotmail.com","A sido un exito","Factura Generada"));
		ResponseEntity<String> enviar = restTemplate.postForEntity("http://localhost:8080/email/send", mailBody, String.class);
		
		return"redirect:/tickets";
	}
	
	
	
	
	private ArrayList<ProductInCart> getCart(HttpServletRequest request){
		ArrayList<ProductInCart> cart = (ArrayList<ProductInCart>)request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new ArrayList<>();
		}
		return cart;
	}
	
	private void saveCart(ArrayList<ProductInCart> cart, HttpServletRequest request) {
		request.getSession().setAttribute("cart", cart);
	}
	
	private void emptyCart(HttpServletRequest request) {
	    this.saveCart(new ArrayList<>(), request);
	}
	
}
