package com.neosoft.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.neosoft.rest.model.User;

@Service
public interface UserService {
	List<User> getAllUsers(String query, String orderBy, String dir);
	
	Optional<User> findById(int userId);

	User save(User user);

	void delete(int userId);
}
