package com.fradou.accounting.controller;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.Operation;
import com.fradou.accounting.model.OperationRepository;
import com.fradou.accounting.utils.OperationCategory;

@RestController
public class OperationController {

	@Autowired
	OperationRepository dao;

	@GetMapping("/operation")
	public List<Operation> getOperations(
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
		else if (queryParam >=2 && queryParam < 4) {
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
				throw new IllegalArgumentException("Month without year isn't allowed !");
		}
	}
	
	@GetMapping("/operation/{id}")
	public Operation getOperation(@PathVariable("id") int id) {
		return dao.findById(id).get();
	}

	@PostMapping("/operation")
	public int createOperation(@RequestBody Operation entry) {
		return dao.save(entry).getId();
	}
}
