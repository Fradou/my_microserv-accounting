package com.fradou.accounting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Report;
import com.fradou.accounting.model.ReportRepository;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportRepository dao;
	
	@GetMapping
	public List<Report> getGlobalReport(
			@RequestParam(value="category", required=false) String category){
		
	/**	if(category != null) {
			return dao.findByReportIdReportCategory(OperationCategory.valueOf(category));
		}
		else { **/
			return (List<Report>) dao.findAll();
	//	}
	}
}
