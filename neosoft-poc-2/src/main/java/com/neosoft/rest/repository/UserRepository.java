package com.neosoft.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT * FROM users u "
			+ "WHERE first_name LIKE %?1 OR last_name LIKE %?1 OR pin_code LIKE %?1 order by ?2 ?3", nativeQuery = true)
	List<User> findAllByNameOrLastNameOrPincode(String queryParam, String column, String dir);
}