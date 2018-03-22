package com.fradou.accounting.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fradou.accounting.utils.OperationCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ReportId implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
	private LocalDate operationMonth;
	
	@Enumerated(EnumType.ORDINAL)
	@JsonProperty("category")
	private OperationCategory operationCategory;

	public ReportId(LocalDate operationMonth, OperationCategory operationCategory) {
		this.operationMonth = operationMonth;
		this.operationCategory = operationCategory;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationCategory == null) ? 0 : operationCategory.hashCode());
		result = prime * result + ((operationMonth == null) ? 0 : operationMonth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportId other = (ReportId) obj;
		if (operationCategory != other.operationCategory)
			return false;
		if (operationMonth == null) {
			if (other.operationMonth != null)
				return false;
		} else if (!operationMonth.equals(other.operationMonth))
			return false;
		return true;
	}
}
