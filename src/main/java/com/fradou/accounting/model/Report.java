package com.fradou.accounting.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.accounting.utils.BigDecimalSerializer;

@Entity
@Immutable
@Table(name="v_report")
@Data
public class Report {

	@EmbeddedId
	@JsonUnwrapped
	private ReportId id;
	
	private BigDecimal amount;
	
	@JsonPropertyOrder({"category", "amount"})
	public interface Total {
		
		@JsonProperty("category")
		Category getReportCategory();
		
		@JsonSerialize(using = BigDecimalSerializer.class)
		BigDecimal getAmount();
	}
}
