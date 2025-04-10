package com.cases.join.publisher.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Emerson Oliveira
 * 
 */
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(String topic, Object message) {
		try {
			String json = objectMapper.writeValueAsString(message);
			kafkaTemplate.send(topic, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
