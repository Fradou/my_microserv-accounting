package com.fradou.accounting.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fradou.accounting.utils.OperationCategory;

public interface OperationRepository extends CrudRepository<Operation, Integer> {

	public List<Operation> findByOperationDate(LocalDate date);
	
	public List<Operation> findByOperationCategory(OperationCategory category);
	
	@Query("select o from Operation o where YEAR(operationDate)=:year and MONTH(operationDate)=:month")
	public List<Operation> findByMonthAndYear(@Param("year") int year,@Param("month") int month);
}
