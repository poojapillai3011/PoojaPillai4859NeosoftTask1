package com.example.sbformvalidation.controller;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbformvalidation.entity.User;
import com.example.sbformvalidation.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    UserService userService;

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> get(@PathVariable Long uid) {
        try {
            User user = userService.getUser(uid);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    
	/*
	 * @GetMapping("/{firstName}") public ResponseEntity<User> get(@PathVariable
	 * String firstName) { try { User user =
	 * userService.getUserFirstName(firstName); return new
	 * ResponseEntity<User>(user, HttpStatus.OK); } catch (NoSuchElementException e)
	 * { return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 */
    
    @GetMapping("/firstName/{firstname}")
	public List<User> searchUserbyFirstName(@PathVariable String firstname) {
		return userService.searchUserbyFirstName(firstname);
	}
    
    @GetMapping("/lastName/{lastname}")
  	public List<User> searchUserbyLastName(@PathVariable String lastname) {
  		return userService.searchUserbyLastName(lastname);
  	}
    
    @GetMapping("/pincode/{pincode}")
  	public List<User> searchUserbypincode(@PathVariable String pincode) {
  		return userService.searchUserbypincode(pincode);
  	}
    
    
	@GetMapping("/sortdob")
	public List<User> sortUserbydob() {
		return userService.sortUserbydob();
	}

	
	@GetMapping("/sortjoiningdate")
	public List<User> sortUserbyjoiningdate() {
		return userService.sortUserbyjoiningdate();
	}
    
    @RequestMapping(value = "/registerNewUser", method = RequestMethod.POST)
	public User registerUser(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}
    
    
	/*
	 * @PostMapping("/") public void add(@RequestBody User user) {
	 *return  userService.saveUser(user); }
	 */
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        try {
            User existUser = userService.getUser(id);
            user.setUid(id);            
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
