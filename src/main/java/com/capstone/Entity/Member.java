package com.capstone.Entity;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.type.TrueFalseConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private String gender;
	private LocalDate dob;
	@Column(unique = true)
	private String email,password;
	@Column(length = 10,unique = true)
	private long mobile;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB", length = Integer.MAX_VALUE)
	private byte[] img;
	
	@OneToOne(cascade = CascadeType.ALL)
	Address address;
	private boolean disabled;
	
    
}
