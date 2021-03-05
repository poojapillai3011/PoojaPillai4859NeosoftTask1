package com.example.sbformvalidation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.sbformvalidation.entity.User;
import com.example.sbformvalidation.repository.UserRepository;
import com.example.sbformvalidation.service.UserService;
//import com.neosoft.exception.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void registerUser() {
		User user = new User(5L, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789", "Active",
				new Date(), new Date());
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.registerUser(user));
	}

	@Test
	public void updateUserTest() {
		User user = new User(5L, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789", "Active",
				new Date(), new Date());
		long id = user.getUid();
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.getUser(id);
		Assert.assertNotNull(result);
		//assertEquals(user, result);
	}

	/*
	 * @Test public void updateUserTest2() { User user = new User(5L, "Pooja",
	 * "Pillai", "pooja@gmail.com", "400890", "9829356789", "Active", new Date(),
	 * new Date()); long id = user.getUid();
	 * when(userRepository.save(user)).thenReturn(user); User result =
	 * userService.updateUser2(id, user); Assert.assertNotNull(result);
	 * //assertEquals(user, result); }
	 */

	
	@Test
	public void deleteUser() {
		User user = new User(5L, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789", "Active",
				new Date(), new Date());
		long id = user.getUid();
		userService.deleteUser(id);
		verify(userRepository, times(1)).deleteById(id);
	}

	@Test
	public void listAllUser() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(3l, "Pooja", "Pillai", "pooja@gmail.com",
				"400890", "9829356789", "Active", new Date(), new Date())).collect(Collectors.toList()));
		assertEquals(1, userService.listAllUser().size());
	}

	@Test
	public void getUserbyfirstameTest() {
		String firstname = "Pooja";
		when(userRepository.findByFirstname(firstname))
				.thenReturn(Stream.of(new User(3l, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789",
						"Active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.searchUserbyFirstName(firstname).size());
	}

	@Test
	public void searchUserbylastNameTest() {
		String lastname = "Pillai";
		when(userRepository.findByLastname(lastname))
				.thenReturn(Stream.of(new User(3l, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789",
						"Active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.searchUserbyLastName(lastname).size());
	}

	@Test
	public void searchUserbyPinCodeTest() {
		String pincode = "400890";
		when(userRepository.findByPincode(pincode))
				.thenReturn(Stream.of(new User(3l, "Pooja", "Pillai", "pooja@gmail.com", "400890", "9829356789",
						"Active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.searchUserbypincode(pincode).size());
	}

	@Test
	public void sortUserbydob() {
		List<User> listuser = new ArrayList<>();
		String dob = "2021";
		when(userRepository.findByOrderByDobAsc()).thenReturn(listuser);
		List<User> result = userService.sortUserbydob(dob);
		Assert.assertNotNull(result);
	}

	@Test
	public void sortUserbyjoiningdate() {
		List<User> listuser = new ArrayList<>();
		String joiningdate = "2021";
		when(userRepository.findAllByOrderByJoiningdateAsc()).thenReturn(listuser);
		List<User> result = userService.sortUserbyjoiningdate(joiningdate);
		Assert.assertNotNull(result);
	}

}
