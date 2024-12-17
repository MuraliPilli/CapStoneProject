package com.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.Entity.Orders;
@Repository
public interface Orderrepository extends JpaRepository<Orders, Integer> {

}
