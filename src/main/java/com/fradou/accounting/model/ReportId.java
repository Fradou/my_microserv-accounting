package com.fradou.accounting.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ReportId implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
	private LocalDate reportMonth;
	
	@JsonProperty("category")
	private Category reportCategory;

	public ReportId(LocalDate reportMonth, Category reportCategory) {
		this.reportMonth = reportMonth;
		this.reportCategory = reportCategory;
	}
	
	public ReportId() {
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reportCategory == null) ? 0 : reportCategory.hashCode());
		result = prime * result + ((reportMonth == null) ? 0 : reportMonth.hashCode());
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
		if (reportCategory != other.reportCategory)
			return false;
		if (reportMonth == null) {
			if (other.reportMonth != null)
				return false;
		} else if (!reportMonth.equals(other.reportMonth))
			return false;
		return true;
	}
}
