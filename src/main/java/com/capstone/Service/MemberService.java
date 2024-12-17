package com.capstone.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.DAO.MembersDao;
import com.capstone.Entity.Member;
import com.capstone.ExceptionHandler.NotFoundException;
import com.capstone.util.SuccessResponse;
@Service
public class MemberService
{
	@Autowired
	MembersDao dao;
	
	
	//to customize the HTTp response that your Spring applictaion sends to client
	public ResponseEntity<SuccessResponse> saveMember(Member member)
	{
		SuccessResponse data= SuccessResponse
				.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDateTime.now())
				.data(dao.registeration(member))
				.msg("Admin Details saved Successfully").build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
	}
	
	public Member loginmember(String email,String password)
	{
		Member login = dao.Login(email);
		if(login.getEmail().equalsIgnoreCase(email))
		{
			if(login.getPassword().equalsIgnoreCase(password))
			{
//				SuccessResponse data= SuccessResponse
//				.builder()
//				.status(HttpStatus.CREATED.value())
//				.dateTime(LocalDateTime.now())
//				.data(login)
//				.msg("member Details saved Successfully").build();
//		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
				return login;
			}else
			{
				throw new NotFoundException("admin "+password +" is not found in database");
			}
		}else
		{
			throw new NotFoundException("admin "+email +" is not found in database");
		}
	}
	
	public ResponseEntity<SuccessResponse> updatedetails(int id,String password)
	{
		Optional<Member> fetchbyid = dao.fetchbyid(id);
		if(fetchbyid.isPresent())
		{
			fetchbyid.get().setPassword(password);
		    dao.registeration(fetchbyid.get());
			SuccessResponse data= SuccessResponse
					.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDateTime.now())
					.data(fetchbyid)
					.msg("Member Details updated Successfully").build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}else
		{
			 throw new NotFoundException("Member "+id +" is not found in database");
		}
	}
	
	public ResponseEntity<SuccessResponse> deletedetails(int id)
	{
		Optional<Member> fetchbyid = dao.fetchbyid(id);
		if(fetchbyid.isPresent())
		{
			dao.deleltebyid(fetchbyid.get().getId());
			SuccessResponse data= SuccessResponse
					.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDateTime.now())
					.data(fetchbyid)
					.msg("Member Details deleted Successfully").build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}else
		{
			throw new NotFoundException("Member "+id +" is not found in database");
		}
	}
	
	public ResponseEntity<SuccessResponse> image( int mid,MultipartFile file) throws IOException
	{
//		System.out.println(file.getBytes());
//		System.out.println(file.getOriginalFilename());
		Optional<Member> fetchbyid = dao.fetchbyid(mid);
		if(fetchbyid.isPresent())
		{
			fetchbyid.get().setImg(file.getBytes());
			dao.registeration(fetchbyid.get());
			SuccessResponse data= SuccessResponse
					.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDateTime.now())
					.data(fetchbyid)
					.msg("Member image inserted  Successfully").build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}
		return null;
	}
	
	public ResponseEntity<byte[]> fetchimage( int mid)
	{
//		System.out.println(file.getBytes());
//		System.out.println(file.getOriginalFilename());
		Optional<Member> fetchbyid = dao.fetchbyid(mid);
		if(fetchbyid.isPresent())
		{
			byte[] img = fetchbyid.get().getImg();
			org.springframework.http.HttpHeaders headers=new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			
			return new ResponseEntity<byte[]>(img,headers,HttpStatus.FOUND);
		}
		return null;
	}
	
	
	
}
