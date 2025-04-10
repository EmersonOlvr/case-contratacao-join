package com.cases.join.publisher.web.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cases.join.models.PersonDTO;
import com.cases.join.publisher.service.person.PersonService;

import jakarta.validation.Valid;

/**
 * @author Emerson Oliveira
 * 
 */
@RestController
@RequestMapping("/api/v1/person")
@CrossOrigin(origins = "*")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid PersonDTO person) {
		this.service.save(person);
		return ResponseEntity.noContent().build();
	}
	
}
