package repository;

import entities.Book;
import entities.Loan;
import entities.Member;
import enums.LoanStatus;

import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    private List<Loan> loans = new ArrayList<>();

    public void saveLoan(Loan loan) {
        loans.add(loan);
    }

    public Loan findLoanById(String id) {
        for (Loan loan : loans) {
            if (id.equals(loan.getIdLoan())) {
                return loan;
            }
        }
        return null;
    }

    public Loan findActiveLoanByBook(Book book) {
        for(Loan loan : loans) {
            if(loan.getBook().equals(book) && loan.getStatus().equals(LoanStatus.ACTIVE)) {
                return loan;
            }
        }
        return null;
    }

    public List<Loan> findActiveLoansByMember(Member member) {
        List<Loan> resultLoans = new ArrayList<>();
        for(Loan loan : loans) {
            if(loan.getMember().equals(member) && loan.getStatus().equals(LoanStatus.ACTIVE)) {
                resultLoans.add(loan);
            }
        }

        return resultLoans;
    }

    public List<Loan> listAllLoans() {
        return loans;
    }
}
