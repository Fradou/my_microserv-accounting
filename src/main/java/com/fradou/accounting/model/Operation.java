package com.fradou.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	private BigDecimal amount;

	@JsonProperty("date")
	private LocalDate operationDate;

	@Column(length = 100)
	private String comment;
}
