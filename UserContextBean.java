package com.ibm.promptppt;

import org.springframework.stereotype.Component;

import com.ibm.promptppt.model.User;

@Component
public class UserContextBean {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
