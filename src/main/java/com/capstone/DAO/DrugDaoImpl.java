package com.capstone.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.Entity.Drug;
import com.capstone.Repository.DrugRepository;
@Component
public class DrugDaoImpl implements DrugDao {

	@Autowired
	DrugRepository repository;
	@Override
	public Drug insertionofdrug(Drug d) {
	
		return repository.save(d);
	}
	@Override
	public Optional<Drug> findbyid(int id) {
		
		return repository.findById(id);
	}
	@Override
	public void deletebyid(int id) {
		repository.deleteById(id);
		
	}
	@Override
	public Drug fetchbyname(String name) {
		return repository.findByName(name);
		
	}
	@Override
	public List<Drug> allDrugs() {
		List<Drug> findAll = repository.findAll();
		return findAll;
	}
	@Override
	public List<Drug> filter(String name) {
		List<Drug> readByNameAllIgnoreCase = repository.readByNameAllIgnoreCase(name);
		return readByNameAllIgnoreCase;
	}

}
