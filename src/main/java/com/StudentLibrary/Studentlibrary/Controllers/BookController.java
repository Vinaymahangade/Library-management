package com.StudentLibrary.Studentlibrary.Controllers;

import com.StudentLibrary.Studentlibrary.Model.Book;
import com.StudentLibrary.Studentlibrary.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "false") boolean available,
            @RequestParam(required = false) String author) {

        return bookService.getBooks(genre, available, author);
    }
}
