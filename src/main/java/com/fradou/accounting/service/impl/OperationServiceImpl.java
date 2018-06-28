package com.fradou.accounting.service.impl;

import com.fradou.accounting.model.Operation;
import com.fradou.accounting.service.OperationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Override
    public List<Operation> getByCategory(String category) {
        return null;
    }

    @Override
    public List<Operation> getByCategoryAndDate(String category, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Operation> getByDate(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
