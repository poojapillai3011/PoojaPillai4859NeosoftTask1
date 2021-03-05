package com.example.sbformvalidation.service;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbformvalidation.entity.User;
import com.example.sbformvalidation.repository.UserRepository;
@Service
@Transactional
public class UserService {
	@Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Long uid) {
        return userRepository.findById(uid).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
  
	public List<User> sortUserbydob(String dob) {
		return userRepository.findByOrderByDobAsc();
	}

	public List<User> sortUserbyjoiningdate(String joiningdate) {
		return userRepository.findAllByOrderByJoiningdateAsc();
	}
	
	  @Transactional // register new user
		public User registerUser(User user) {
			return userRepository.save(user);
		}

	public List<User> searchUserbyFirstName(String firstname) {
		// TODO Auto-generated method stub
		return userRepository.findByFirstname(firstname);
	}

	public List<User> searchUserbyLastName(String lastname) {
		// TODO Auto-generated method stub
		return userRepository.findByLastname(lastname);
	}

	public List<User> searchUserbypincode(String pincode) {
		// TODO Auto-generated method stub
		return userRepository.findByPincode(pincode);
	}

	    

	/*
	 * public User getUserFirstName(String firstName) { // TODO Auto-generated
	 * method stub return userRepository.findByFirstName(firstName); }
	 */

}
