package com.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capstone.Entity.Admin;

import jakarta.transaction.Transactional;


public interface AdminRepository extends JpaRepository<Admin, Integer> 
{
     Admin  findByEmail(String email);
     @Modifying
     @Transactional
     @Query("UPDATE Admin a SET a.password = :password WHERE a.email = :email")
     int updatePasswordByEmail(String email, String password);
     
}
