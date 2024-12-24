package com.chandu.service;

import com.chandu.dto.LoanDTO;
import com.chandu.exception.ResourceNotFoundException;
import com.chandu.model.Loan;
import com.chandu.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceTest implements LoanService {

    private final LoanRepository loanRepository;
    
    

    public LoanServiceTest(LoanRepository loanRepository) {
		super();
		this.loanRepository = loanRepository;
	}

	@Override
    public Loan createLoan(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setBorrowerName(loanDTO.getBorrowerName());
        loan.setAmount(loanDTO.getAmount());
        loan.setStatus(loanDTO.getStatus());
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = getLoanById(id);
        loan.setBorrowerName(loanDTO.getBorrowerName());
        loan.setAmount(loanDTO.getAmount());
        loan.setStatus(loanDTO.getStatus());
        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        Loan loan = getLoanById(id);
        loanRepository.delete(loan);
    }
}
