package com.cases.join.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Emerson Oliveira
 * 
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	private String id;
	
	private String name;

	@NotBlank(message = "O CPF é obrigatório")
	@Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
	private String cpf;

	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "Informe um e-mail válido")
	private String email;
	
	private String phone;

}
