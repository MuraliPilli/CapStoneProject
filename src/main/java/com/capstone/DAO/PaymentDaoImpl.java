package com.capstone.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Drug;
import com.capstone.Entity.Payments;
import com.capstone.Repository.DrugRepository;
import com.capstone.Repository.PaymentRepository;
@Component
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	PaymentRepository repository;
	@Override
	public Payments savedrug(Payments p) {
		
		return repository.save(p);
	}

}
