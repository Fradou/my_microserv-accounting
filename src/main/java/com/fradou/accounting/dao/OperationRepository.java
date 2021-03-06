package com.fradou.accounting.dao;

import java.time.LocalDate;
import java.util.List;

import com.fradou.accounting.model.Category;
import com.fradou.accounting.model.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Integer> {

	public List<Operation> findByOperationDate(LocalDate date);
	
	public List<Operation> findByCategory(Category category);
	
	public List<Operation> findByOperationDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<Operation> findByCategoryAndOperationDateBetween(Category category, LocalDate startDate, LocalDate endDate);
}
