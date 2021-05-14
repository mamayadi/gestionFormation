package spring.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.model.Personne;
import spring.jpa.repository.PersonneRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private PersonneRepository personneRepos;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public JwtUserDetailsService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Personne personne = personneRepos.findByUsername(username);
		if (personne == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(personne.getUsername(), personne.getPassword(),
				personne.getAuthorities());
	}
	
	public Personne getUserByUsername(String username)throws UsernameNotFoundException {
		Personne personne = personneRepos.findByUsername(username);
		if (personne == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return personne;
	}

	public Personne save(Personne personne) {
		Personne newPersonne = new Personne();
		newPersonne.setUsername(personne.getUsername());
		newPersonne.setPassword(bcryptEncoder.encode(personne.getPassword()));
		return personneRepos.save(newPersonne);
	}

}
