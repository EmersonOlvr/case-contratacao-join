package com.cases.join.publisher.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cases.join.publisher.kafka.KafkaProducerService;
import com.cases.join.publisher.kafka.KafkaTopics;

/**
 * @author Emerson Oliveira
 * 
 */
@Service
public class PersonService {

	@Autowired
    private KafkaProducerService kafkaProducerService;
	
	public void save(PersonDTO person) {
		this.kafkaProducerService.sendMessage(KafkaTopics.TOPIC_CREATE_PERSON, person);
	}

}
