package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<String> createBook(Book book);

    ResponseEntity<List<Book>> getBooks(String genre, boolean available, String author);
}
