package entities;

import enums.LoanStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private final String idLoan;
    private Member member;
    private Book book;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan(String idLoan, Member member, Book book, LocalDate dueDate) {
        this.idLoan = idLoan;
        this.member = member;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.dueDate = dueDate;
        this.status = LoanStatus.ACTIVE;
    }

    public String getIdLoan() {
        return idLoan;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(idLoan, loan.idLoan);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idLoan);
    }

    public void finalizeLoan(LocalDate returnDate){
        if(returnDate.isBefore(loanDate)){
            throw new IllegalArgumentException("Date impossible");
        }

        this.returnDate = returnDate;
        setStatus(LoanStatus.RETURNED);
    }

    public boolean isOverdue(){
        return status == LoanStatus.ACTIVE && LocalDate.now().isAfter(dueDate);
    }
}
