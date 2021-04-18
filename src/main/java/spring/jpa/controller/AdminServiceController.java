package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Admin;
import spring.jpa.service.interfaces.AdminService;

@RestController
public class AdminServiceController {
	@Autowired
	AdminService adminService;

	public AdminServiceController() {
	}

	@RequestMapping(value = "/admins", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getAdmins() {
		return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
	}

	@RequestMapping(value = "/admins", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createAdmin(@RequestBody Admin admin) {
		return new ResponseEntity<Object>(adminService.createAdmin(admin), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/admins/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) {
		return new ResponseEntity<Object>(adminService.updateAdmin(id, admin), HttpStatus.OK);
	}

	@RequestMapping(value = "/admins/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getAdmin(@PathVariable("id") Long id) {
		return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admins/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteAdmin(@PathVariable("id") Long id) {
		adminService.deleteAdmin(id);
		return new ResponseEntity<>("Admin is deleted successsfully", HttpStatus.OK);
	}
	
}
