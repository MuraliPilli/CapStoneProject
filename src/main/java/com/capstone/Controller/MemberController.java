package com.capstone.Controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;


import com.capstone.Entity.Member;
import com.capstone.Repository.MembersRepository;
import com.capstone.Service.MemberService;
import com.capstone.util.SuccessResponse;

@RestController
@RequestMapping("/home")
public class MemberController
{
	
	@Autowired
	MemberService service;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Member member)
	{
		
		return service.saveMember(member);
		
	}
	@GetMapping("/memberlogin")
	public Member memberlogin( String email,String password)
	{
		return service.loginmember(email, password);
	}
	@PutMapping("/updatemember")
	public ResponseEntity<SuccessResponse> updatemembers(@RequestParam("id") int id,@RequestParam("password") String password)
	{
		return service.updatedetails(id, password);
	}
	@DeleteMapping("/deletedetails")
	public ResponseEntity<SuccessResponse> deletebyid( @RequestParam int id)
	{
		return service.deletedetails(id);
	}
	@PutMapping("/uplodeimage")
	public ResponseEntity<SuccessResponse> uplodeimage(@RequestParam int mid,@RequestParam MultipartFile file) throws IOException
	{
//		System.out.println(file.getBytes());
//		System.out.println(file.getOriginalFilename());
		return service.image(mid, file);
	}
	
	@GetMapping("/fetchimage")
	public ResponseEntity<byte[]> getimage(@RequestParam int mid)
	{
		return service.fetchimage(mid);
	}
	
}
