package com.fradou.accounting.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fradou.accounting.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Report;
import com.fradou.accounting.dao.ReportRepository;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportRepository dao;
	
	@GetMapping("/total/{year}")
	public List<Report.Total> getYearlyTotalReport(
			@PathVariable int year,
			@RequestParam(required=false) boolean average
		){
		
		LocalDate startDate = LocalDate.ofYearDay(year, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfYear());
		
		if(average) {
			return dao.getTotalReport(startDate, endDate, BigDecimal.valueOf(12));
		}
		else {
			return dao.getTotalReport(startDate, endDate);
		}
	}
	
	@GetMapping("/total/{startDate}/{endDate}")
	public List<Report.Total> getCustomTotalReport(
			@PathVariable YearMonth startDate,
			@PathVariable YearMonth endDate,
			@RequestParam(required=false) boolean average
			){
		
		if(average) {
			return dao.getTotalReport(startDate.atDay(1), endDate.atEndOfMonth(), BigDecimal.valueOf(Math.max(startDate.until(endDate, ChronoUnit.MONTHS),1)));
		}
		else {
			return dao.getTotalReport(startDate.atDay(1), endDate.atEndOfMonth());
		}
	}
	
	@GetMapping("/detailed/{year}")
	public Map<LocalDate, Map<Category, BigDecimal>> getYearlyDetailedReport(@PathVariable int year){

		LocalDate startDate = LocalDate.ofYearDay(year, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfYear());
		
		return getMappedResults(dao.findByIdReportMonthBetweenOrderByIdReportMonthAscIdReportCategoryAsc(startDate, endDate));
	}
	
	@GetMapping("/detailed/{startDate}/{endDate}")
	public Map<LocalDate, Map<Category, BigDecimal>> getCustomDetailedReport(
			@PathVariable YearMonth startDate,
			@PathVariable YearMonth endDate
			){
		return getMappedResults(dao.findByIdReportMonthBetweenOrderByIdReportMonthAscIdReportCategoryAsc(startDate.atDay(1), endDate.atEndOfMonth()));
	}
	
	private Map<LocalDate, Map<Category, BigDecimal>> getMappedResults(List<Report> reports){
		
		Map<LocalDate, Map<Category, BigDecimal>> formattedResults = new HashMap<LocalDate, Map<Category, BigDecimal>>();
		
		for(Report report : reports) {
			Map<Category, BigDecimal> subMap = formattedResults.getOrDefault(report.getId().getReportMonth(), new HashMap<Category, BigDecimal>());
			subMap.put(report.getId().getReportCategory(), report.getAmount());
			formattedResults.put(report.getId().getReportMonth(), subMap);
		}
		
		return formattedResults;
	}
}
