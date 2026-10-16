package entities;

import exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class
    Member {
    private final String idMember;
    private String name;
    private String email;

    private final List<Book> bookList = new ArrayList<>();

    public Member(String idMember, String name, String email) {
        this.idMember = idMember;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    public String getIdMember() {
        return idMember;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;
        return Objects.equals(idMember, member.idMember);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMember);
    }

    public void borrowBook(Book book) {
        if(bookList.contains(book)) {
            throw new BookAlreadyExistsException("Book already exists");
        }

        if(bookList.size() >= 5) {
            throw new LoanLimitExceededException("Loan limit reached");
        }

        book.borrowBook();
        bookList.add(book);
    }

    public void returnBook(Book book) {
        if(!bookList.contains(book)) {
            throw new BookNotBorrowedException("Book is not borrowed");
        }
        book.returnBook();
        bookList.remove(book);
    }
}
