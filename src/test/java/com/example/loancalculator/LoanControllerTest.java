package com.example.loancalculator;

import com.example.loancalculator.dto.LoanRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    public static final String PATH = "/api/loans/schedule";
    public static final String MESSAGE_PATH = "$.message";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getSchedule_positiveCase() throws Exception {
        LoanRequest request = generateLoanRequest();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(12))
                .andExpect(jsonPath("$[0].month").value(1));
    }


    @Test
    void getSchedule_whenLoanAmountIsZero() throws Exception {
        LoanRequest request = generateLoanRequest();
        request.setLoanAmount(0);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(MESSAGE_PATH, containsString("Loan amount must be greater than 0")));

    }

    @Test
    void getSchedule_whenInterestRateIsZero() throws Exception {
        LoanRequest request = generateLoanRequest();
        request.setAnnualInterestRate(0);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(MESSAGE_PATH, containsString("Annual interest rate must be greater than 0")));
    }

    @Test
    void getSchedule_whenTermIsLessThanOne() throws Exception {
        LoanRequest request = generateLoanRequest();
        request.setTermInMonths(0);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(MESSAGE_PATH, containsString("Term in months must be at least 1")));
    }

    @Test
    void getSchedule_whenTermMoreThan420() throws Exception {
        LoanRequest request = generateLoanRequest();
        request.setTermInMonths(421);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(MESSAGE_PATH, containsString("Max Loan term is 35 years or 420 months")));
    }


    private static LoanRequest generateLoanRequest() {
        LoanRequest request = new LoanRequest();
        request.setLoanAmount(100000);
        request.setAnnualInterestRate(12);
        request.setTermInMonths(12);
        return request;
    }
}

