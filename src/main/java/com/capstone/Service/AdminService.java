package com.capstone.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.DAO.AdminDao;
import com.capstone.Entity.Admin;
import com.capstone.ExceptionHandler.NotFoundException;
import com.capstone.Repository.AdminRepository;
import com.capstone.util.SuccessResponse;

@Service
public class AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	
	//to customize the HTTp response that your Spring applictaion sends to client
	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin)
	{
		SuccessResponse data= SuccessResponse
				.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDateTime.now())
				.data(adminDao.saveAdmin(admin))
				.msg("Admin Details saved Successfully").build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
	}
	
	
	
	public ResponseEntity<SuccessResponse> fetchAdmin(int  id)
	{
		Optional<Admin> fetchDetails = adminDao.fetchDetails(id);
		if(fetchDetails.isPresent()) {
		SuccessResponse data= SuccessResponse
				.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDateTime.now())
				.data(adminDao.fetchDetails(id))
				.msg("Admin Details fectch Successfully").build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);}
		else {
			 throw new NotFoundException("admin "+ id +" is not found in database") ;
		}
	}
	
	
	
	public Admin loginAdmin(String email,String password )
	{
		Admin by = adminDao.getByEmailAndPassword(email);
		if(by.getEmail().equalsIgnoreCase(email)) 
		{
			if (by.getPassword().equals(password))
			{
//				SuccessResponse data= SuccessResponse
//						.builder()
//						.status(HttpStatus.FOUND.value())
//						.dateTime(LocalDateTime.now())
//						.data(by)
//						.msg("Admin Details fectch Successfully").build();
				return by;
				}	
			else {
				throw new NotFoundException("admin "+password +" is not found in database");
			}
					}
		
	
		else {
			 throw new NotFoundException("admin "+ email+" is not found in database") ;
		}
		
		
	}
	
	
	
	
	
	public ResponseEntity<SuccessResponse> deleteAdmin(int id )
	{
		Optional<Admin> fetchDetails = adminDao.fetchDetails(id);
		
		
			if (fetchDetails.isPresent())
			{
				adminDao.deleteById(fetchDetails.get().getId());
				SuccessResponse data= SuccessResponse
						.builder()
						.status(HttpStatus.FOUND.value())
						.dateTime(LocalDateTime.now())
						.data(fetchDetails)
						.msg("Admin Details deleted Successfully").build();
				return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
				}	
			else {
				throw new NotFoundException("admin "+id +" is not found in database");
			}
				
		
	}
	
	
	@Autowired
	AdminRepository ar;
	public int UpdateAdmin(String email,String Password )
	{
		
		Admin findByEmail = ar.findByEmail(email);
		if (findByEmail !=null) {
			int updatepssword = adminDao.updatepssword(email, Password);
			return updatepssword;
		} else {
			return 0;
		}
		
//			if (updatepssword >0 )
//			{
//				
//			    adminDao.saveAdmin(updatepssword);
//				SuccessResponse data= SuccessResponse
//						.builder()
//						.status(HttpStatus.FOUND.value())
//						.dateTime(LocalDateTime.now())
//						.data(updatepssword)
//						.msg("Admin Details updated Successfully").build();
//				return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
//				}	
//			else {
//				throw new NotFoundException("admin "+email+" is not found in database");
//			}
		
				
		
	}
	

}
