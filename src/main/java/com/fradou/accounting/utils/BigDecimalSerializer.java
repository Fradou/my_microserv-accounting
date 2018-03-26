package com.fradou.accounting.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		if(value == null) {
			jgen.writeNull();
		}
		else {
			jgen.writeNumber(value.setScale(2, RoundingMode.HALF_EVEN));
		}
	}
}
