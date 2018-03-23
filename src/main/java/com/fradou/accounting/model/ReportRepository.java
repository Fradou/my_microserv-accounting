package com.fradou.accounting.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fradou.accounting.utils.OperationCategory;

public interface ReportRepository extends CrudRepository<Report, ReportId> {

	List<Report> findByIdReportCategory(OperationCategory reportCategory);
}
