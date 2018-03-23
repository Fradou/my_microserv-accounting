package com.fradou.accounting.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Report;
import com.fradou.accounting.model.ReportRepository;
import com.fradou.accounting.utils.OperationCategory;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportRepository dao;

	@GetMapping
	public List<Report> getGlobalReport(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) String month,
			@RequestParam(required = false) Boolean detailed
			) {

		if (category != null) {
			return dao.findByIdReportCategory(OperationCategory.valueOf(category));
		} else {
			// return (List<Report>) dao.findAll();
			
			LocalDate startDate = LocalDate.now();
			LocalDate endDate = startDate.minusYears(1);
			System.out.println("Voilou voilou, start : " + startDate.toString() + " and end : " + endDate.toString());
			
			List<Report.Total> results = dao.getGlobalReport(startDate, endDate);
			for(Report.Total catego : results) {
				System.out.println("catego : " + catego.getReportCategory() + " - " + catego.getAmount());
			}
			
			return (List<Report>) dao.findAll();
		}
	}
	
	/**@GetMapping("/year")
	public List<Report>
	
	@GetMapping("/year/{year}")
	public List<Report> getYearReport**/
}
