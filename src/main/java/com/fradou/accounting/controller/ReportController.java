package com.fradou.accounting.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Report;
import com.fradou.accounting.model.ReportRepository;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportRepository dao;
	
	@GetMapping("/total/{year}")
	public List<Report.Total> getYearlyAverageRaport(@PathVariable int year){
		
		LocalDate startDate = LocalDate.ofYearDay(year, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfYear());
		
		return dao.getTotalReport(startDate, endDate);
	}
	
	@GetMapping("/total/{startDate}/{endDate}")
	public List<Report.Total> getCustomAverageReport(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
			){
		return dao.getTotalReport(startDate, endDate);
	}
	
	@GetMapping("/detailed/{year}")
	public List<Report> getYearlyDetailedReport(@PathVariable int year){

		LocalDate startDate = LocalDate.ofYearDay(year, 1);
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfYear());
		
		return dao.getDetailedReport(startDate, endDate);
	}
	
	@GetMapping("/detailed/{startDate}/{endDate}")
	public List<Report> getCustomDetailedReport(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
			){
		return dao.getDetailedReport(startDate, endDate);
	}
}
