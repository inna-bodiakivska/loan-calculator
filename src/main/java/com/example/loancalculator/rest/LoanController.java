package com.example.loancalculator.rest;

import com.example.loancalculator.dto.LoanPayment;
import com.example.loancalculator.dto.LoanRequest;
import com.example.loancalculator.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/schedule")
    public List<LoanPayment> getSchedule(@Valid @RequestBody LoanRequest request) {
        return loanService.getSchedule(request);
    }

}



