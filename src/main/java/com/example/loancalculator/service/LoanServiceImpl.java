package com.example.loancalculator.service;

import com.example.loancalculator.dto.LoanPayment;
import com.example.loancalculator.dto.LoanRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    public List<LoanPayment> getSchedule(LoanRequest request) {
        double loanAmount = request.getLoanAmount();
        double monthlyInterestRate = request.getAnnualInterestRate() / 12 / 100;
        int term = request.getTermInMonths();

        double annuityPayment = loanAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -term)));

        List<LoanPayment> schedule = new ArrayList<>();
        double remainingBalance = loanAmount;

        for (int month = 1; month <= term; month++) {
            double interest = remainingBalance * monthlyInterestRate;
            double principal = annuityPayment - interest;
            remainingBalance -= principal;

            schedule.add(
                    new LoanPayment(
                            month,
                            round(annuityPayment),
                            round(principal),
                            round(interest),
                            round(Math.max(remainingBalance, 0))
                    )
            );
        }
        return schedule;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
