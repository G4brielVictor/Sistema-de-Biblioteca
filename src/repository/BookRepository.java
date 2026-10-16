package repository;

import entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books = new ArrayList<>();

    public void saveBook(Book newBook) {
        books.add(newBook);
    }

    public void removeBook(Book removedBook) {
        books.remove(removedBook);
    }

    public List<Book> listAllBooks(){
        return books;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if(book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if(book.getISBN().equalsIgnoreCase(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBookByAuthor(String author) {
        List<Book> resultBooks = new ArrayList<>();

        for (Book book : books) {
            if(book.getAuthor().equalsIgnoreCase(author)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }
}
