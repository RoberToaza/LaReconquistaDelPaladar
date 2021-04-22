package es.urjc.code.dad.web.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.repository.ClientRepository;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		return "login_template";
	}

}
