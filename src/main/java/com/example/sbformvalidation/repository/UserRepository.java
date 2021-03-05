package com.example.sbformvalidation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbformvalidation.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByFirstname(String firstname);
	
	public List<User> findByLastname(String lastname);
	
	public List<User> findByPincode(String pincode);

	public List<User> findByOrderByDobAsc();
		
    public List<User> findAllByOrderByJoiningdateAsc();

	public void deleteById(Long id);

	//public Optional<User> findById(Integer id);  
}