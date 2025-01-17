package com.capstone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
	private String name;
	@Column(unique = true)
	private String email,password;
	private String role;
	
	
	
}
