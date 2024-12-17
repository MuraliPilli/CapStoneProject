package com.capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Entity.Drug;
import com.capstone.ExceptionHandler.NotFoundException;
import com.capstone.Service.AdminService;
import com.capstone.Service.DrugService;
import com.capstone.util.SuccessResponse;

@RestController
@RequestMapping("/drug")
public class DrugController {
	@Autowired
	DrugService service;
	@Autowired
	AdminService ser;
	
	
	@PostMapping("/davedrug")
	public ResponseEntity<SuccessResponse> insert(@RequestBody Drug d,@RequestParam int id)
	{
		ResponseEntity<SuccessResponse> fetchAdmin = ser.fetchAdmin(id);
		if(fetchAdmin !=null)
		{
		return service.save(d);
		}else
		{
			throw new NotFoundException("admin "+id +" is not found in database");
		}
	}
	@PutMapping("/updatedrug")
	public ResponseEntity<SuccessResponse> update(@RequestParam int adid,@RequestParam int id,@RequestParam double price,@RequestParam int quantaty)
	{
		ResponseEntity<SuccessResponse> fetchAdmin = ser.fetchAdmin(adid);
		if(fetchAdmin !=null){
			return service.updatedrug(id, price, quantaty);
		}else
		{
			throw new NotFoundException("admin "+id +" is not found in database");
		}
	}
	@DeleteMapping("/deletedrug")
	public ResponseEntity<SuccessResponse> deletebyid(@RequestParam int adid,@RequestParam int id)
	{
		ResponseEntity<SuccessResponse> fetchAdmin = ser.fetchAdmin(adid);
		if(fetchAdmin !=null)
		{
			return service.deletedrug(id);
		}else
		{
			throw new NotFoundException("admin "+id +" is not found in database");
		}
	}
}
