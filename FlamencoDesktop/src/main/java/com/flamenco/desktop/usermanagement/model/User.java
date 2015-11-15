package com.flamenco.desktop.usermanagement.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement
public class User implements Serializable {
	
	@XmlTransient
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String username;
	private String password;
	private String email;   
    
    public User(){
    	super();
    }
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public StringProperty idProperty() {
		return new SimpleStringProperty(id.toString());
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}	
	public StringProperty usernameProperty() {
		return new SimpleStringProperty(username);
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public StringProperty passwordProperty() {
		return new SimpleStringProperty(password);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public StringProperty emailProperty() {
		return new SimpleStringProperty(email);
	}	
    
}
