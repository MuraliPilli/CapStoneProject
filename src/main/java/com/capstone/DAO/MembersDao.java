package com.capstone.DAO;

import java.util.Optional;

import com.capstone.Entity.Admin;
import com.capstone.Entity.Member;

public interface MembersDao 
{
   Member registeration(Member member);
   Member Login(String email);
   Optional<Member> fetchbyid(int id);
   void deleltebyid(int id);
}
