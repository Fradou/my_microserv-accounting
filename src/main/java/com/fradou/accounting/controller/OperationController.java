package com.fradou.accounting.controller;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;
import java.util.List;

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
			@RequestParam(value="type", required=false) String type,
			@RequestParam(value="month", required=false) Integer month,
			@RequestParam(value="year", required=false) Integer year){
		
		if(month != null && year != null) {
			
			LocalDate startDate = LocalDate.of(year, month, 1);
			LocalDate endDate = startDate.with(lastDayOfMonth());
			
			if(type!=null) {
				return dao.findByOperationCategoryAndOperationDateBetween(OperationCategory.valueOf(type.toUpperCase()), startDate, endDate);
			}
			else {
				return dao.findByOperationDateBetween(startDate, endDate);
			//	return dao.findByMonthAndYear(year, month);
			}
		}
		else if (type!=null) {
			return dao.findByOperationCategory(OperationCategory.valueOf(type.toUpperCase()));
		}
		else {
			return (List<Operation>) dao.findAll();
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
