package com.cases.join.consumer.kafka;

import java.util.Set;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cases.join.consumer.domain.person.Person;
import com.cases.join.consumer.repository.person.PersonRepository;
import com.cases.join.models.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumerPerson {
	
	@Autowired
	private final PersonRepository personRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@KafkaListener(topics = "person.create", groupId = "pessoa-consumer")
	public void listen(ConsumerRecord<String, String> record) {
		try {
			System.out.println("JSON recebido: " + record.value());
			
			PersonDTO person = objectMapper.readValue(record.value(), PersonDTO.class);

			// (re)valida antes de salvar
			Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
			if (!violations.isEmpty()) {
				System.out.println("[X] Dados inválidos recebidos: ");
				violations.forEach(v -> System.out.println("- " + v.getMessage()));
				return;
			}

			this.personRepository.save(this.unconvert(person));
			System.out.println("[OK] Pessoa salva com sucesso: " + person.getCpf());

		} catch (Exception e) {
			System.err.println("Erro ao processar mensagem Kafka: " + e.getMessage());
		}
	}
	
	private PersonDTO convert(Person person) {
		return PersonDTO.builder()
						.id(person.getUuid() != null ? person.getUuid().toString() : null)
						.name(person.getName())
						.cpf(person.getCpf())
						.email(person.getEmail())
						.telefone(person.getTelefone())
						.build();
	}
	
	private Person unconvert(PersonDTO person) {
		return Person.builder()
						.uuid(person.getId() != null ? UUID.fromString(person.getId()) : null)
						.name(person.getName())
						.cpf(person.getCpf())
						.email(person.getEmail())
						.telefone(person.getTelefone())
						.build();
	}

}
