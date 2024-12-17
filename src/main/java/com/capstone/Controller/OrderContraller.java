package com.capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.capstone.Entity.Orders;
import com.capstone.Service.Orderssarvice;

@Controller
public class OrderContraller {
	@Autowired
	Orderssarvice os;
	
	public void orderplaced(Orders o)
	{
		os.orderssave(o);;
	}
}
