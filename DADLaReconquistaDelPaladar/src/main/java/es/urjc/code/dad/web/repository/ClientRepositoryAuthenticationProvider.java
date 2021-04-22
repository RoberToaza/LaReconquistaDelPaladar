package es.urjc.code.dad.web.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.code.dad.web.model.Client;

@Component
public class ClientRepositoryAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private ClientRepository clientsRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		Client c = clientsRepository.findByFirstName(auth.getName());
		
		if (c == null) {
			throw new BadCredentialsException("Client not found");
		}
		
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, c.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : c.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return new UsernamePasswordAuthenticationToken(c.getFisrtName(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	

}
