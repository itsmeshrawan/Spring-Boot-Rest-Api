package com.neosoft.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.neosoft.rest.annotation.PasswordValueMatch;
import com.neosoft.rest.annotation.ValidPassword;
import com.neosoft.rest.util.Auditable;

import lombok.Data;

@PasswordValueMatch.List({
		@PasswordValueMatch(
				field = "password", 
				fieldMatch = "confirmPassword", 
				message = "Passwords do not match!") 
		})
@Data
@Entity
@Table(name = "users")
public class User extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "First name should not be empty")
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@NotBlank(message = "First name should not be empty")
	@Size(min = 3, max = 30, message = "Email should have atleast {min}-{max} characters")
	@Email(message = "Email should be valid")
	private String email;

	@Email(message = "Email should be valid")
	@Column(name = "secondary_email", length = 30)
	private String secondaryEmail;

	@ValidPassword
	@NotBlank(message = "Password should not be empty")
	@Size(min = 5, max = 30, message = "Password should have atleast {min}-{max} characters")
	private String password;
	
	@ValidPassword
	@Transient
	private String confirmPassword;

	@NotBlank(message = "Phone should not be empty")
	@Column(name = "phone", length = 15)
	private String phone;

	@Column(name = "secondary_phone", length = 15)
	private String secondaryPhone;

	@NotNull(message = "DOB should not be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@NotBlank(message = "Gender should not be empty")
	@Column(name = "gender", length = 20)
	private String gender;

	@NotBlank(message = "Blood Group should not be empty")
	@Column(name = "blood_group", length = 5)
	private String bloodGroup;

	@NotBlank(message = "Pan Number should not be empty")
	@Column(name = "pan_number", length = 10)
	private String panNumber;

	@NotBlank(message = "Aadhar Number should not be empty")
	@Column(name = "aadhar_number", length = 12)
	private String aadharNumber;

	private boolean isDeleted;

	@NotBlank(message = "Address Line 1 should not be empty")
	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@NotBlank(message = "City should not be empty")
	@Column(name = "city", length = 100)
	private String city;

	@NotBlank(message = "State should not be empty")
	@Column(name = "state", length = 100)
	private String state;

	@NotBlank(message = "Pin Code should not be empty")
	@Column(name = "pin_code", length = 16)
	private String pinCode;

	@NotBlank(message = "Country should not be empty")
	@Column(name = "country", length = 100)
	private String country;

	@NotBlank(message = "Company Name should not be empty")
	@Column(name = "company_name", length = 100)
	private String companyName;

	@NotBlank(message = "Designation should not be empty")
	@Column(name = "designation", length = 100)
	private String designation;

	@NotBlank(message = "Location should not be empty")
	@Column(name = "location", length = 100)
	private String location;

	@NotNull(message = "Joining Date should not be empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joiningDate;
}
