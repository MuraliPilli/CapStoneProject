package com.capstone.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int memberid;
	@OneToMany
	List<Drug> drugs;
	private double amount;

}
