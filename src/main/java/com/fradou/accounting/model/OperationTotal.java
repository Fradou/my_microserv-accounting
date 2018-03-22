package com.fradou.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fradou.accounting.utils.OperationCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Immutable
@Table(name="v_operation_total")
public class OperationTotal {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
	LocalDate operationMonth;
	
	@Enumerated(EnumType.ORDINAL)
	@JsonProperty("category")
	private OperationCategory operationCategory;
	
	private BigDecimal amount;
}
