package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Author;
import org.springframework.http.ResponseEntity;

public interface AuthorService {

    ResponseEntity<String> createAuthor(Author author);

    ResponseEntity<String> updateAuthor(Author author);

    ResponseEntity<String> deleteAuthor(int id);
}
