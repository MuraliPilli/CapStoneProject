package com.capstone.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Address;
import com.capstone.Repository.AddressRepository;
@Component
public class AddressDaoImpl implements AddressDao {
	@Autowired
	AddressRepository ar;
	@Override
	public Address saveaddress(Address a) {
		
		return ar.save(a);
	}

}
