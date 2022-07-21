package com.mongodb.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.api.Repository.TutorialRepository;

import Pojo.Tutorial;
@Service
public class TutorialService {

	@Autowired
	public TutorialRepository tutoRepo;
	//@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
	  try {
	    Tutorial _tutorial = tutoRepo.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
	    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		  try {
		    List<Tutorial> tutorials = new ArrayList<Tutorial>();
		    if (title == null)
		    	tutoRepo.findAll().forEach(tutorials::add);
		    else
		    	tutoRepo.findByTitleContaining(title).forEach(tutorials::add);
		    if (tutorials.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(tutorials, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		}
	
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
		  Optional<Tutorial> tutorialData = tutoRepo.findById(id);
		  if (tutorialData.isPresent()) {
		    return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		}
	
	public ResponseEntity<List<Tutorial>> findByPublished() {
		  try {
		    List<Tutorial> tutorials = tutoRepo.findByPublished(true);
		    if (tutorials.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(tutorials, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		}
	
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
		  Optional<Tutorial> tutorialData = tutoRepo.findById(id);
		  if (tutorialData.isPresent()) {
		    Tutorial _tutorial = tutorialData.get();
		    _tutorial.setTitle(tutorial.getTitle());
		    _tutorial.setDescription(tutorial.getDescription());
		    _tutorial.setPublished(tutorial.isPublished());
		    return new ResponseEntity<>(tutoRepo.save(_tutorial), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		}
	
	
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
		  try {
			  tutoRepo.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		}
	
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  try {
			  tutoRepo.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		}
	
}
