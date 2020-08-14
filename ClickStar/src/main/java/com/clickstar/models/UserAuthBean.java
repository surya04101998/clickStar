package com.clickstar.models;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class UserAuthBean {
	@Id
	private BigInteger id;
	private String username;
	private String password;
	public UserAuthBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
		


}
