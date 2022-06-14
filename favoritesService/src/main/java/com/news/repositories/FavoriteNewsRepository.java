package com.news.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.news.models.NewsModel;

@Repository
public interface FavoriteNewsRepository extends MongoRepository<NewsModel,String> {

}
