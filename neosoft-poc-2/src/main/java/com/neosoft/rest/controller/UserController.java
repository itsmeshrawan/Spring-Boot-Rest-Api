package com.neosoft.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.rest.exception.EntityNotFoundException;
import com.neosoft.rest.model.User;
import com.neosoft.rest.service.UserService;
import com.neosoft.rest.util.Constants;

@RestController
@RequestMapping(Constants.USERS)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(
			@RequestParam(required = false) String query,
			@RequestParam(required = false) String orderBy, 
			@RequestParam(required = false) String dir) {
		
		return new ResponseEntity<>(userService.getAllUsers(query, orderBy, dir), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		User user = userService.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User id not found - " + userId));

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
		} catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
		Optional<User> userData = userService.findById(userId);

		if (userData.isPresent()) {
			User _user = userData.get();

			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setEmail(user.getEmail());
			_user.setSecondaryEmail(user.getSecondaryEmail());
			_user.setPhone(user.getPhone());
			_user.setSecondaryPhone(user.getSecondaryPhone());
			_user.setDob(user.getDob());
			_user.setGender(user.getGender());
			_user.setBloodGroup(user.getBloodGroup());
			_user.setPanNumber(user.getPanNumber());
			_user.setAadharNumber(user.getAadharNumber());
			_user.setAddressLine1(user.getAddressLine1());
			_user.setAddressLine2(user.getAddressLine2());
			_user.setCity(user.getCity());
			_user.setState(user.getState());
			_user.setPinCode(user.getPinCode());
			_user.setCountry(user.getCountry());
			_user.setCompanyName(user.getCompanyName());
			_user.setDesignation(user.getDesignation());
			_user.setLocation(user.getLocation());
			_user.setJoiningDate(user.getJoiningDate());

			return new ResponseEntity<>(userService.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/tempDelete/{userId}")
	public ResponseEntity<User> tempDeleteUser(@PathVariable("userId") int userId) {
		Optional<User> userData = userService.findById(userId);

		if (userData.isPresent()) {
			User _user = userData.get();

			_user.setDeleted(true);

			userService.save(_user);

			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
		try {
			userService.delete(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
