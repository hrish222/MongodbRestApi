package com.mongodb.api.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.api.Service.TutorialService;

import Pojo.Tutorial;
@RestController
//@RequestMapping("/api")
public class TutorialController {

	

	
	  @Autowired
	  public TutorialService tutoService;
	//  TutorialRepository tutorialRepository;
	  @GetMapping("/tutorials")
	  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
	   return  tutoService.getAllTutorials(title);
	    
	  }
	  
	  @GetMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
		return tutoService.getTutorialById(id);
	    
	  }
	  @PostMapping("/tutorials")
	  public String createTutorial(@RequestBody Tutorial tutorial) {
	    tutoService.createTutorial(tutorial);
	    return tutorial.getId();
	  }
	  @PutMapping("/tutorials/{id}")
	  public String updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
	      tutoService.updateTutorial(id, tutorial);
	      return tutorial.getTitle();
	  }
	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
	    return tutoService.deleteTutorial(id);
	  }
	  
}


