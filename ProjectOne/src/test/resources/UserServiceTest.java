package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.data.UserDAO;


public class UserServiceTest {
	private static UserService service = null;
	private static User u;
	private static UserDao ud = null;
	
	
	
	
	@BeforeAll
	public static void setUpClass() {
		u = new User();
		u.setUsername("Test");
		
		
	}
	
	@BeforeEach
	public void setUpTests() {
		service = new UserService();
		//u.setLastCheckIn(LocalDate.of(2021, 1, 1));
		service.ud = Mockito.mock(UserDAO.class);
		
	}
	
	@Test
	public void testLogin() {
		Mockito.when(ud.getUser(u.getUsername()).thenReturn(u);
		ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
		User loginUser = service.login(user.getUsername());
		assertEquals(user, loginUser, "Login works.");

		assertEquals(user.getUsername(), usernameCaptor.getValue(),
				"Checking if user name is correct ");
	
	