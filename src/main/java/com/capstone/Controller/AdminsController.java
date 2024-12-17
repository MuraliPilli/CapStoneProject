package com.capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Entity.Admin;
import com.capstone.Service.AdminService;
import com.capstone.util.SuccessResponse;

@RestController
@RequestMapping("/admin")//it is used as classlevel which is used to base package
public class  AdminsController {
	@Autowired
	AdminService adminservice;
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Admin admin)
	{
		return adminservice.saveAdmin(admin);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<SuccessResponse>   fetchAdmin(@RequestParam int id)
	{
		return adminservice.fetchAdmin(id);
	}

	@GetMapping("/login")
	public Admin login(@RequestParam String email,@RequestParam String password)
	{
		return adminservice.loginAdmin(email,password);
				
	}

	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse>   deleteAdmin(@RequestParam int id)
	{
		return adminservice.deleteAdmin(id);
				
	}
	
	@PutMapping("/update")
	public int   updateAdmin(@RequestParam String Email,@RequestParam String password)
	{
		return adminservice.UpdateAdmin(Email, password);
				
	}
	

	
	

}
