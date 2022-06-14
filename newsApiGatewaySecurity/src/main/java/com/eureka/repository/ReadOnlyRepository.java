package com.eureka.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReadOnlyRepository<T,ID> extends MongoRepository<T,ID> {
	boolean existsById(ID id);
}
