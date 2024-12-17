package com.capstone.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Admin;
import com.capstone.Repository.AdminRepository;
@Component
public class AdminDaoImpl implements AdminDao {

	@Autowired
	AdminRepository aRep;
	@Override
	public Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return aRep.save(admin);
	}
	@Override
	public Optional<Admin> fetchDetails(int id) {
		
	Optional<Admin> byId = aRep.findById(id);
//	if (byId.isPresent())
//	{
//		Admin admin = byId.get();
//		return Optional.of(admin);
//	}
//	return null;
//	}
	return byId;

}
	@Override
	public Admin getByEmailAndPassword(String email) {
		return aRep.findByEmail(email);
	}
	@Override
	public void deleteById(int id) {
		 aRep.deleteById(id);;
	}
	@Override
	public int updatepssword(String email, String password) {
		
		return aRep.updatePasswordByEmail(email, password);
	}
	
}
