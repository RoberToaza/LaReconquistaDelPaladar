package es.urjc.code.dad.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.code.dad.web.model.Client;

@Component
public class ClientRepositoryAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException{
		Client client = clientRepository.findById(auth.getName()).get();
		
		if(client == null) {
			throw new BadCredentialsException("User not found");
		}
		
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, client.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		return new UsernamePasswordAuthenticationToken(client.getFisrtName(), password);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
