package com.example.genestore.vo.request;

import lombok.Data;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

@Data
public class LoginForm {
  
    private String username;
   
    public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	private String password;

	
}