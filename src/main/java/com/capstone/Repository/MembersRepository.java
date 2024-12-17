package com.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.Entity.Member;

public interface MembersRepository extends JpaRepository<Member, Integer>
{
	Member findByEmail(String email);
}
