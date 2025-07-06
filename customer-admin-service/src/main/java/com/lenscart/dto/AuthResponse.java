package com.lenscart.dto;

public class AuthResponse {
    private String token;
    private String message;
    private String role;
    private Long customerId;

    

    public AuthResponse(String token, String message, String role, Long customerId) {
		super();
		this.token = token;
		this.message = message;
		this.role = role;
		this.customerId = customerId;
	}

	public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
    
}
