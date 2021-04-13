package es.urjc.code.dad.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		return "login_template";
	}
	
	@RequestMapping("/error2")
	public String error() {
		return "error_template";
	}

}
