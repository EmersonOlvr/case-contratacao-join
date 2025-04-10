package com.cases.join.consumer.domain.person;

import org.hibernate.annotations.DynamicInsert;

import com.cases.join.consumer.domain.UUIDEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "person")
@DynamicInsert
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person extends UUIDEntity {
	
	private static final long serialVersionUID = -6785576418371262274L;
	
	@Column
	private String name;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private String telefone;

}
