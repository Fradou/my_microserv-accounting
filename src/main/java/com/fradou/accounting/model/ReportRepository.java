package com.fradou.accounting.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fradou.accounting.utils.OperationCategory;

public interface ReportRepository extends CrudRepository<Report, ReportId> {

	List<Report> findByIdReportCategory(OperationCategory reportCategory);
	
	List<Report> findByIdReportMonthBetween(LocalDate startDate, LocalDate endDate);
	
	@Query("select r.id.reportCategory as reportCategory, sum(r.amount) as amount from Report as r group by r.id.reportCategory")
	List<Report.Total> getGlobalReport(LocalDate startDate, LocalDate endDate);
}
