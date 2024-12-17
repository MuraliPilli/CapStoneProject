package com.capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.Entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Integer> {
	Drug findByName(String name);
	List<Drug> readByNameAllIgnoreCase(String name);
}
