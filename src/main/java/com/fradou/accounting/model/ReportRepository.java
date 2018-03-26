package com.fradou.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fradou.accounting.utils.OperationCategory;

public interface ReportRepository extends CrudRepository<Report, ReportId> {

	List<Report> findByIdReportCategory(OperationCategory reportCategory);

	List<Report> findByIdReportMonthBetween(LocalDate startDate, LocalDate endDate);

	@Query("select r.id.reportCategory as reportCategory, sum(r.amount) as amount from Report as r where r.id.reportMonth >= ?1 and r.id.reportMonth <= ?2 group by r.id.reportCategory")
	List<Report.Total> getTotalReport(LocalDate startDate, LocalDate endDate);
	
	@Query("select r.id.reportCategory as reportCategory, sum(r.amount)/?3 as amount from Report as r where r.id.reportMonth >= ?1 and r.id.reportMonth <= ?2 group by r.id.reportCategory")
	List<Report.Total> getTotalReport(LocalDate startDate, LocalDate endDate, BigDecimal average);

	@Query("select function('DATE_FORMAT', r.id.reportMonth, '%Y-%m-01') as reportMonth, r.id.reportCategory as reportCategory, sum(r.amount) as amount from Report as r where r.id.reportMonth >= ?1 and r.id.reportMonth <= ?2 group by function('DATE_FORMAT', r.id.reportMonth, '%Y-%m-01'), r.id.reportCategory")
	List<Report> getDetailedReport(LocalDate startDate, LocalDate endDate);
	
}
