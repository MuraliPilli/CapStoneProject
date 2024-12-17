package com.capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Service.PaymentService;
import com.capstone.util.SuccessResponse;

@RestController
@RequestMapping("/payment")
public class PaymentsController {
	@Autowired
	PaymentService ser;
	
	@PostMapping("/order")
	public ResponseEntity<SuccessResponse> orderdrugs(@RequestParam int mid,@RequestParam List<String> drugname)
	{
		return ser.order(mid, drugname);
	}
}
