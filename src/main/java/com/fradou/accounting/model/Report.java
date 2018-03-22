package com.fradou.accounting.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Immutable
@Table(name="v_report")
public class Report {

	@EmbeddedId
	private ReportId id;
	
	private BigDecimal amount;
}
