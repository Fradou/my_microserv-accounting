package com.fradou.accounting.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fradou.accounting.utils.OperationCategory;

public interface OperationTotalRepository extends CrudRepository<OperationTotal, Integer> {

	public List<OperationTotal> findByOperationCategory(OperationCategory category);
}
