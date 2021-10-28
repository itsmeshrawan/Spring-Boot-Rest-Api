package com.neosoft.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email"), 
		@UniqueConstraint(columnNames = "phone") 
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String secondaryEmail;
	private String password;
	private String confirmPassword;
	private String phone;
	private String secondaryPhone;
	private String dob;
	private String gender;
	private String bloodGroup;
	private String panNumber;
	private String aadharNumber;
}
