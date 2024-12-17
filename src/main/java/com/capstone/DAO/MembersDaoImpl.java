package com.capstone.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Member;
import com.capstone.Repository.MembersRepository;
@Component
public class MembersDaoImpl implements MembersDao {

	@Autowired
	MembersRepository rip;
	
	@Override
	public Member registeration(Member member) {
		return  rip.save(member);
	}

	@Override
	public Member Login(String email) {
		return rip.findByEmail(email);
		
	}

	@Override
	public Optional<Member> fetchbyid(int id) {
		Optional<Member> findById = rip.findById(id);
		return findById;
	}

	@Override
	public void deleltebyid(int id) {
		rip.deleteById(id);
		
	}

}
