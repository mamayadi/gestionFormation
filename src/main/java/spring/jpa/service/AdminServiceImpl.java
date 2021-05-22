package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Admin;
import spring.jpa.repository.AdminRepository;
import spring.jpa.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminRepos;

	public AdminServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Admin createAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepos.save(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		return adminRepos.findAll();
	}

	@Override
	public Admin updateAdmin(Long id, Admin admin) {
		Admin foundedAdmin = getAdminById(id);
		if (admin.getNom() != null) {
			foundedAdmin.setNom(admin.getNom());
		}
		if (admin.getPrenom() != null) {
			foundedAdmin.setPrenom(admin.getPrenom());
		}
		if (admin.getPassword() != null) {
			foundedAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
		}
		return adminRepos.save(foundedAdmin);
	}

	@Override
	public Admin getAdminById(Long id) {
		return adminRepos.findById(id).orElseThrow(() -> new NotFoundException("Admin not found!"));
	}

	@Override
	public void deleteAdmin(Long id) {
		Admin admin = getAdminById(id);
		adminRepos.delete(admin);
	}

	/******* End Admin CRUD ******/
}
