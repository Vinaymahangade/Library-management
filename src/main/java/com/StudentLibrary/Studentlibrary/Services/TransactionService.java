package com.StudentLibrary.Studentlibrary.Services;

import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<String> issueBook(int cardId, int bookId) throws Exception;

    ResponseEntity<String> returnBook(int cardId, int bookId) throws Exception;
}
