package com.capstone.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.DAO.DrugDao;
import com.capstone.Entity.Drug;
import com.capstone.ExceptionHandler.NotFoundException;
import com.capstone.util.SuccessResponse;
@Service
public class DrugService {
	@Autowired
	DrugDao dao;
	
	public ResponseEntity<SuccessResponse> save(Drug d)
	{
		SuccessResponse data= SuccessResponse
				.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDateTime.now())
				.data(dao.insertionofdrug(d))
				.msg("Drug Details saved Successfully").build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
	}
	
	public ResponseEntity<SuccessResponse> updatedrug(int id,double price,int quantaty)
	{
		Optional<Drug> findbyid = dao.findbyid(id);
		if(findbyid.isPresent())
		{
			findbyid.get().setPrice(price);
			findbyid.get().setQuantity(quantaty);
			dao.insertionofdrug(findbyid.get());
			SuccessResponse data= SuccessResponse
					.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDateTime.now())
					.data(findbyid)
					.msg("Drug Details updated Successfully").build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}else
		{
			 throw new NotFoundException("member "+ id +" is not found in database") ;
		}
	}
	
	public ResponseEntity<SuccessResponse> deletedrug(int id)
	{
		Optional<Drug> findbyid = dao.findbyid(id);
		if(findbyid.isPresent())
		{
			dao.deletebyid(findbyid.get().getId());
			SuccessResponse data= SuccessResponse
					.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDateTime.now())
					.data(findbyid)
					.msg("Drug Details deleted Successfully").build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}else
		{
			throw new NotFoundException("Drug "+id +" is not found in database");
		}
	}
}
