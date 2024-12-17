package com.capstone.DAO;

import java.util.List;
import java.util.Optional;

import com.capstone.Entity.Drug;
import com.capstone.Entity.Member;

public interface DrugDao {
	Drug insertionofdrug(Drug d);
	  Optional<Drug> findbyid(int id);
	  void deletebyid(int id);
	  Drug fetchbyname(String name);
	  List<Drug> allDrugs();
	  List<Drug> filter(String name);
}
