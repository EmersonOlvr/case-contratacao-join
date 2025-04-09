package com.cases.join.publisher.serialization;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
	
	public static final DateTimeFormatter ISO_8601_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx")
																				.withZone(ZoneId.of("UTC"));

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		if (value == null) {
			throw new IOException("OffsetDateTime argument is null.");
		}

		jsonGenerator.writeString(ISO_8601_FORMATTER.format(value));
	}
	
}
