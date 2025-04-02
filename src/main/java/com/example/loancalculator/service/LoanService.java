package com.example.loancalculator.service;

import com.example.loancalculator.dto.LoanPayment;
import com.example.loancalculator.dto.LoanRequest;

import java.util.List;

public interface LoanService {

    List<LoanPayment> getSchedule(LoanRequest request);
}
