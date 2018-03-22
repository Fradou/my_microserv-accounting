package com.fradou.accounting.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fradou.accounting.utils.AccountEntryType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AccountEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private AccountEntryType accountEntryType;
	
	private BigDecimal amount;
}
