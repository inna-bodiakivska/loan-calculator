package com.example.loancalculator.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class LoanRequest {

    @DecimalMin(value = "0.01", message = "Loan amount must be greater than 0")
    private double loanAmount;

    @DecimalMin(value = "0.01", message = "Annual interest rate must be greater than 0")
    private double annualInterestRate;

    @Min(value = 1, message = "Term in months must be at least 1")
    @Max(value = 420, message = "Max Loan term is 35 years or 420 months")
    private int termInMonths;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }
}
