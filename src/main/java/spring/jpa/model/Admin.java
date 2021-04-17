package spring.jpa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import spring.jpa.enums.Role;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Personne {
	@Id
	@GeneratedValue
	private Long id;
	
	public Admin(String nom, String prenom, String username, String password) {
		super(nom, prenom, username, password, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

}
