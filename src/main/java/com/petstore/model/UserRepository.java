package com.petstore.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	Object saveAll(User[] users);

	@Query("FROM USER WHERE user_name=:username")
	User findByUserName(@Param("username")String username);

	void deleteByUsername(String username);

}
