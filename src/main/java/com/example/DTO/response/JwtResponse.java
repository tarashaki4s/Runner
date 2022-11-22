package com.example.DTO.response;
import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";

	private String username;
	private String email;

	private String fullName;
	private Boolean gender;
	private Boolean active;
	private List<String> roles;

	public JwtResponse(String accessToken, String username, String email, String fullName, Boolean gender, Boolean active, String encode, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.fullName = fullName;
		this.gender = gender;
		this.active =active;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<String> getRoles() {
		return roles;
	}
}
