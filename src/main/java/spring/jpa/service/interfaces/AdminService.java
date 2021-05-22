package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Admin;

public interface AdminService {

	public abstract Admin createAdmin(Admin admin);

	public abstract List<Admin> getAdmins();

	public abstract Admin updateAdmin(Long id, Admin admin);

	public abstract Admin getAdminById(Long id);

	public abstract void deleteAdmin(Long id);

}
