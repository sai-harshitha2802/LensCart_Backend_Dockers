package com.lenscart.dto;
import com.lenscart.entity.Admin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class AdminDTO {



    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

	public AdminDTO(@NotBlank(message = "Name cannot be empty") String name,
			@Email(message = "Invalid email format") @NotBlank(message = "Email cannot be empty") String email,
			@NotBlank(message = "Password cannot be empty") String password) {
		super();
		
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public AdminDTO() {
		super();
	}
	
	
	public AdminDTO(Admin admin) {
      
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
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
    
    
}
