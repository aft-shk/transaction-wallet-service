package com.wallet.transaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Builder;


@Entity
@Table(name = "users")

@Builder
public class User {


	public User() {
		
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String clientId, String name, Role role) {
		super();
		this.userId = userId;
		this.clientId = clientId;
		this.name = name;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long  userId;
	    
	    
	 	@Column(name = "client_id", nullable = false, unique = true)
	    private String clientId; 
	    
	 	@Column(nullable = false)
	    private String name;
	    
	 	@Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;
	    
	    
	    
	   
	    
	    
	}