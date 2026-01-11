package com.StudentLibrary.Studentlibrary.Services.Implimentation;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Repositories.AuthorRepository;
import com.StudentLibrary.Studentlibrary.Services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<String> createAuthor(Author author) {
        authorRepository.save(author);
        return new ResponseEntity<>("Author created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateAuthor(Author author) {
        int rowsUpdated = authorRepository.updateAuthorDetails(author);

        if (rowsUpdated > 0) {
            return new ResponseEntity<>("Author updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteAuthor(int id) {
        int rowsDeleted = authorRepository.deleteCustom(id);

        if (rowsDeleted > 0) {
            return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }
}
