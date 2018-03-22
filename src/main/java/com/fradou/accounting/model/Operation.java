package com.fradou.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fradou.accounting.utils.OperationCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private OperationCategory operationCategory;
	
	private BigDecimal amount;

	private LocalDate operationDate;
}
