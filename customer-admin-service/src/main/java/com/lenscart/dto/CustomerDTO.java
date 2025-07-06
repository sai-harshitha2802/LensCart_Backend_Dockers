package com.lenscart.dto;

import com.lenscart.entity.Customer;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

	@NotBlank(message = "Name cannot be empty")
	private String name;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in|org|net)$", message = "Email must be valid and end with .com, .in, .org, or .net")
	@NotBlank(message = "Email cannot be empty")
	private String email;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters, include uppercase, lowercase, number, and special character")
	@NotBlank(message = "Password cannot be empty")
	private String password;

	@NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phoneNumber;

	public CustomerDTO(@NotBlank(message = "Name cannot be empty") String name,
			@Email(message = "Invalid email format") @NotBlank(message = "Email cannot be empty") String email,
			@NotBlank(message = "Password cannot be empty") String password,
			@NotBlank(message = "Phone number cannot be empty") String phoneNumber) {
		super();

		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDTO(Customer customer) {
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.phoneNumber = customer.getPhoneNumber();
	}

	public CustomerDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
