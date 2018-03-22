package com.fradou.accounting.controller;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Operation;
import com.fradou.accounting.model.OperationRepository;
import com.fradou.accounting.utils.OperationCategory;

@RestController
public class OperationController {

	@Autowired
	OperationRepository dao;

	@GetMapping("/entry")
	public List<Operation> getAccountEntries(
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="year", required=false) Integer year,
			@RequestParam(value="month", required=false) Integer month
			){
		
		int queryParam = 0;
		
		queryParam += (category != null) ? 1 : 0;
		queryParam += (year != null) ? 2 : 0;
		queryParam += (month != null) ? 4 : 0;
		
		LocalDate startDate = null;
		LocalDate endDate = null;
		
		if(queryParam >= 6) {
			startDate = LocalDate.of(year, month, 1);
			endDate = startDate.with(lastDayOfMonth());
		}
		else if (queryParam >= 2) {
			startDate = LocalDate.of(year, 1, 1);
			endDate = startDate.withDayOfYear(startDate.lengthOfYear());
		}
		
		switch(queryParam) {
			case 0:
				return (List<Operation>) dao.findAll();
			case 1:
				return dao.findByOperationCategory(OperationCategory.valueOf(category.toUpperCase()));
			case 2:
				return dao.findByOperationDateBetween(startDate, endDate);
			case 3:
				return dao.findByOperationCategoryAndOperationDateBetween(OperationCategory.valueOf(category.toUpperCase()), startDate, endDate);
			case 6:
				return dao.findByOperationDateBetween(startDate, endDate);
			case 7:
				return dao.findByOperationCategoryAndOperationDateBetween(OperationCategory.valueOf(category.toUpperCase()), startDate, endDate);
			default:
				throw new RuntimeException();
		}
	}
	
	@GetMapping("/entry/{id}")
	public Operation getAccountEntry(@PathVariable("id") int id) {
		return dao.findById(id).get();
	}

	@PostMapping("/entry")
	public int createAccountEntry(@RequestBody Operation entry) {
		return dao.save(entry).getId();
	}
	
	@GetMapping("/")
	public List<Operation> getMonthly(
			){
		
		return null;
	}
}
