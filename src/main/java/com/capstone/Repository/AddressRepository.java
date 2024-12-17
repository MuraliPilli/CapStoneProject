package com.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
