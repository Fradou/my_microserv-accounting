package com.fradou.accounting.controller;

import java.time.temporal.TemporalAdjusters;

import java.time.LocalDate;
import java.util.List;

import com.fradou.accounting.service.OperationService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fradou.accounting.model.Operation;
import com.fradou.accounting.dao.OperationRepository;

@RestController
@RequestMapping("/operation")
public class OperationController {

	@Autowired
	OperationRepository dao;

	@Autowired
	OperationService operationService;

	@GetMapping
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
			endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
		}
		else if (queryParam >=2 && queryParam < 4) {
			startDate = LocalDate.of(year, 1, 1);
			endDate = startDate.withDayOfYear(startDate.lengthOfYear());
		}
		
		switch(queryParam) {
			case 0:
				return (List<Operation>) dao.findAll();
			case 1:
				return operationService.getByCategory(category);
			case 2:
				return operationService.getByDate(startDate, endDate);
			case 3:
				return operationService.getByCategoryAndDate(category, startDate, endDate);
			case 6:
				return operationService.getByDate(startDate, endDate);
			case 7:
				return operationService.getByCategoryAndDate(category, startDate, endDate);
			default:
				throw new IllegalArgumentException("Month without year isn't allowed !");
		}
	}
	
	@GetMapping("{id}")
	public Operation getOperation(@PathVariable int id) {
		return dao.findById(id).get();
	}

	@PostMapping
	public int createOperation(@RequestBody Operation entry) {
		return dao.save(entry).getId();
	}
	
	@DeleteMapping("{id}")
	public void deleteOperation(@PathVariable int id) {
		dao.deleteById(id);
	}

	@PutMapping("{id}")
	public void updateOperation(@PathVariable int id) {

	}
}
