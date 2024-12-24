package com.chandu.controller;

import com.chandu.dto.LoanDTO;
import com.chandu.model.Loan;
import com.chandu.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    
    
    public LoanController(LoanService loanService) {
		super();
		this.loanService = loanService;
	}

	@PostMapping
    @Operation(summary = "Create a new loan")
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.createLoan(loanDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a loan by ID")
    public ResponseEntity<Loan> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @GetMapping
    @Operation(summary = "Get all loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a loan")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.updateLoan(id, loanDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a loan")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
