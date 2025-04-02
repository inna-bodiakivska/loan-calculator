# 📘 Loan Schedule API

This is a Spring Boot application that calculates a loan repayment schedule using annuity payments. It exposes a REST API and supports Swagger UI for easy testing.

---

## ✅ Features

- Calculate monthly annuity loan payments
- Input validation using Bean Validation
- Global exception handling with custom JSON responses


---

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/inna-bodiakivska/loan-calculator.git
   
   ```

2. **Build and run the application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## 📤 Example Request

```json
POST /api/loans/schedule
Content-Type: application/json

{
  "loanAmount": 100000,
  "annualInterestRate": 12,
  "termInMonths": 12
}
```

---

## 📥 Example Response

```json
[
  {
    "month": 1,
    "payment": 8884.0,
    "principal": 7884.0,
    "interest": 1000.0,
    "balance": 92116.0
  },
  ...
]
```

---

## ❌ Validation & Error Handling

Returns 400 Bad Request with details:

```json
{
  "statusCode": 400,
  "error": "Bad Request",
  "message": "loanAmount: Loan amount must be greater than 0"
}
```


