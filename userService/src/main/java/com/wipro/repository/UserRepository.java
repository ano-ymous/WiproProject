package com.wipro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wipro.model.Users;
public interface UserRepository extends MongoRepository<Users,String> {
	@Query(value = "{email:?0}")
	public Optional<Users> findByEmail(String email);
	
	@Query(value = "{_id:?0}")
	public Optional<Users> findByUsername(String username);
}
