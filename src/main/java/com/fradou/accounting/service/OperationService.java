package com.fradou.accounting.service;

import com.fradou.accounting.model.Operation;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    List<Operation> getByCategory(String category);

    List<Operation> getByCategoryAndDate(String category, LocalDate startDate, LocalDate endDate);

    List<Operation> getByDate(LocalDate startDate, LocalDate endDate);
}
