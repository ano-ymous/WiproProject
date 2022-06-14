package com.wipro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.wipro.model.UserCredentialModel;

public interface UserCredRepository extends MongoRepository<UserCredentialModel,String> {
	@Query(value = "{_id:?0}")
	public Optional<UserCredentialModel> findByEmail(String email);
}
