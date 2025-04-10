package com.cases.join.consumer.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UUIDEntity implements Serializable {

	private static final long serialVersionUID = 398769646853729789L;
	
	@Id
	protected UUID uuid;
	
	@PrePersist
	private void ensureUuid() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
	}

}
