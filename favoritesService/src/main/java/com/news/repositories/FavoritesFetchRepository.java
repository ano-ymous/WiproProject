package com.news.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.news.models.FetchModel;

@Repository
public interface FavoritesFetchRepository extends MongoRepository<FetchModel,String> {
	
}
