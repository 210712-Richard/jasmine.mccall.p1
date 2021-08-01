package com.revature.main;

import com.revature.beans.User;

public interface UserService {

	User login(String name, String password);
}
