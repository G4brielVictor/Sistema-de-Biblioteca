package service;

import entities.*;
import enums.LoanStatus;
import exceptions.BookNotBorrowedException;
import exceptions.BookNotFoundException;
import repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;

public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, BookService bookService, MemberService memberService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public Loan borrowBook(String memberId, String isbn, LocalDate dueDate){
        Member member = memberService.findById(memberId);

        List<Loan> listLoan = loanRepository.findActiveLoansByMember(member);

        for(Loan l : listLoan){
           if(l.isOverdue()){
               l.setStatus(LoanStatus.OVERDUE);
               throw new BookNotBorrowedException("You have a overdue borrow, dont is possible do a new borrow");
           }
        }

        if(dueDate.isBefore(LocalDate.now()) || dueDate.isAfter(LocalDate.now().plusDays(15))){
            throw new IllegalArgumentException("The term of devolution it cannot in past and it cannot to surpass 30 days from now");
        }

        Book book = bookService.findBookByISBN(isbn);

        member.borrowBook(book);

        Loan loan = new Loan(java.util.UUID.randomUUID().toString(), member, book, dueDate);
        loanRepository.saveLoan(loan);

        return loan;
    }

    public void returnBook(String isbn){
        Book book = bookService.findBookByISBN(isbn);

        Loan loan = loanRepository.findActiveLoanByBook(book);
        if(loan == null){
            throw new BookNotBorrowedException("this book is not currently borrowed");
        }

        loan.getMember().returnBook(book);
        loan.finalizeLoan(LocalDate.now());
    }



}
