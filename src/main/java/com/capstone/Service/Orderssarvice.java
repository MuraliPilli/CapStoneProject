package com.capstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.DAO.OrdersDAO;
import com.capstone.Entity.Orders;
@Service
public class Orderssarvice {
	@Autowired
	OrdersDAO od;
	public void orderssave(Orders o)
	{
		od.save(o);
	}
	
}
