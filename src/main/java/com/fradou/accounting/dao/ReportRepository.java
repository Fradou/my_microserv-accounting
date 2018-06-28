package com.fradou.accounting.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fradou.accounting.model.Category;
import com.fradou.accounting.model.Report;
import com.fradou.accounting.model.ReportId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, ReportId> {

	List<Report> findByIdReportCategory(Category reportCategory);

	List<Report> findByIdReportMonthBetweenOrderByIdReportMonthAscIdReportCategoryAsc(LocalDate startDate, LocalDate endDate);

	@Query("select r.id.reportCategory as reportCategory, sum(r.amount) as amount from Report as r where r.id.reportMonth >= ?1 and r.id.reportMonth <= ?2 group by r.id.reportCategory")
	List<Report.Total> getTotalReport(LocalDate startDate, LocalDate endDate);
	
	@Query("select r.id.reportCategory as reportCategory, sum(r.amount)/?3 as amount from Report as r where r.id.reportMonth >= ?1 and r.id.reportMonth <= ?2 group by r.id.reportCategory")
	List<Report.Total> getTotalReport(LocalDate startDate, LocalDate endDate, BigDecimal average);	
}
