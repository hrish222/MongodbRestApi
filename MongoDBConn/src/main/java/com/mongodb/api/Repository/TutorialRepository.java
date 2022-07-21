package com.mongodb.api.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import Pojo.Tutorial;
public interface TutorialRepository extends MongoRepository<Tutorial, String> {
	  List<Tutorial> findByTitleContaining(String title);
	  List<Tutorial> findByPublished(boolean published);
	//String getDescription();
	}
