package com.cases.join.publisher.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 5978598045245891340L;
	
	private static String RESOURCE_NOT_FOUND_MESSAGE = "Não foi possível encontrar %s com %s: %s";
	
	public NotFoundException(String label) {
		super(String.format("Não foi possível encontrar este %s", label));
	}

	public NotFoundException(String label, String id) {
		this(label, "o ID", id);
	}

	public NotFoundException(String label, String field, String id) {
		super(String.format(RESOURCE_NOT_FOUND_MESSAGE, label, field, id));
	}

}
