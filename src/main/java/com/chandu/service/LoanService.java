package com.chandu.service;

import com.chandu.dto.LoanDTO;
import com.chandu.model.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanDTO loanDTO);
    Loan getLoanById(Long id);
    List<Loan> getAllLoans();
    Loan updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoan(Long id);
    
    
    
    
}
