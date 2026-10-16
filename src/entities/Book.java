package entities;

import enums.BookStatus;
import exceptions.BookAlreadyAvailableException;
import exceptions.BookAlreadyExistsException;
import exceptions.BookUnavailableException;

import java.util.Objects;

public class Book {
    private final String ISBN;
    private String title;
    private String author;
    private BookStatus state;

    public Book(String ISBN, String title, String author, BookStatus state) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public Boolean isAvailable() {
        return state == BookStatus.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ISBN);
    }

    public void borrowBook() {
        if(state != BookStatus.AVAILABLE){
            throw new BookUnavailableException("Book is not available");
        }

        state = BookStatus.BORROWED;
    }

    public void returnBook() {
        if(state == BookStatus.AVAILABLE) {
            throw new BookAlreadyAvailableException("Book is already available");
        }

        state = BookStatus.AVAILABLE;
    }

    public String toString() {
        return "     Books of Biblioteca    "+
               "============================"+
               "Author Book: " + getAuthor() +
               "\nTitle Book: " + getTitle() +
               "\nISBN Book: " + getISBN() +
               "\nAvailable" + isAvailable() +
               "============================";
    }
}
