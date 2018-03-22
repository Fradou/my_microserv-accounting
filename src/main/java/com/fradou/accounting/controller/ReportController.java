package com.fradou.accounting.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Report;
import com.fradou.accounting.model.ReportRepository;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportRepository dao;
	
	@GetMapping
	public Map<Integer, Map<String,Report>> getGlobalReport(){
		return null;
		
	}
}
