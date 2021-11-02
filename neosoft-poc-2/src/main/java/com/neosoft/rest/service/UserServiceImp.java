package com.neosoft.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.neosoft.rest.exception.EntityNotFoundException;
import com.neosoft.rest.model.User;
import com.neosoft.rest.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers(String query, String orderBy, String dir) {
		log.info(">>>>> query: " + query + " order: " + orderBy + " dir: " + dir);

		String column = (orderBy != null && (orderBy.equals("dob") || orderBy.equals("joiningDate"))) ? orderBy : "id";
		String columnDir = (dir != null) ? dir : "desc";

		Direction direction = (columnDir.equals("asc")) ? Direction.ASC : Direction.DESC;

		if (query == null)
			return userRepository.findAll(Sort.by(direction, column));
		else
			return userRepository.findAllByNameOrLastNameOrPincode(query, column, columnDir);
	}

	@Override
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(int userId) {
		Optional<User> oldUserEntity = findById(userId);

		if (oldUserEntity.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new EntityNotFoundException("User id not found - " + userId);
		}
	}
}
