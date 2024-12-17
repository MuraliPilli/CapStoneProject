package com.capstone.DAO;

import java.util.Optional;

import com.capstone.Entity.Admin;

public interface AdminDao {
     public Admin saveAdmin(Admin admin);
     
     Optional<Admin> fetchDetails(int id);
     
     int updatepssword(String email,String password);
     Admin getByEmailAndPassword(String email );
     
     void deleteById(int id);
     
     
    
     
}
