package com.fradou.accounting.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fradou.accounting.utils.OperationCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Immutable
@Table(name="v_report")
public class Report {

	@EmbeddedId
	@JsonUnwrapped
	private ReportId id;
	
	private BigDecimal amount;
	
	@JsonPropertyOrder({"category", "amount"})
	public interface Total {
		
		@JsonProperty("category")
		OperationCategory getReportCategory();
		
		BigDecimal getAmount();
	}
}
