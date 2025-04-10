package com.cases.join.consumer.repository.person;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cases.join.consumer.domain.person.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {
	
	

}
