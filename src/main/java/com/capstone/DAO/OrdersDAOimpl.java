package com.capstone.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Orders;
import com.capstone.Repository.Orderrepository;
@Component
public class OrdersDAOimpl implements OrdersDAO {

	@Autowired
	Orderrepository orpo;
	
	@Override
	public Orders save(Orders o) {
		// TODO Auto-generated method stub
		return orpo.save(o);
	}
	
}
