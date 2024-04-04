package com.programming.siva.SpringSecDemo.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	 private Role role;
	
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	    return List.of(new SimpleGrantedAuthority(role.name()));
	  }
	 
	@Override
	public String getPassword() {

		return password;
	}
	
	@Override
	public String getUsername() {
		
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
