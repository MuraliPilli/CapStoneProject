package com.capstone.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.DAO.DrugDao;
import com.capstone.DAO.MembersDao;
import com.capstone.DAO.PaymentDao;
import com.capstone.Entity.Drug;
import com.capstone.Entity.Member;
import com.capstone.Entity.Payments;
import com.capstone.ExceptionHandler.NotFoundException;
import com.capstone.util.SuccessResponse;
@Service
public class PaymentService {

	@Autowired
	DrugDao dao;
	
	@Autowired
	PaymentDao pdao;
	
	@Autowired
	MembersDao mdao;
	@Autowired
	Payments pey;
	
	public ResponseEntity<SuccessResponse> order(int mid,List<String> drugname)
	{
		Optional<Member> fetchbyid = mdao.fetchbyid(mid);
		ArrayList<Drug> druglist=new ArrayList<Drug>();
		
		
		double totalamount=0;
		if(fetchbyid.get()!=null)
		{
			for(String drug:drugname)
			{
				Drug name = dao.fetchbyname(drug);
				if(name != null)
				{
					if(name.getQuantity()>0)
					{
						totalamount += name.getPrice();
						druglist.add(name);
					}else
					{
						throw new NotFoundException("required "+name.getQuantity() +" is not found in database");
					}
				}else
				{
					throw new NotFoundException("the "+drug +" is not found in database");
				}
			}
		}else {
			throw new NotFoundException("Member "+mid +" is not found in database");
		}
		pey.setMemberid(mid);
		pey.setAmount(totalamount);
		pey.setDrugs(druglist);
		pdao.savedrug(pey);
		SuccessResponse data= SuccessResponse
				.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDateTime.now())
				.data(pdao)
				.msg("Member Details deleted Successfully").build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		
	}
}
