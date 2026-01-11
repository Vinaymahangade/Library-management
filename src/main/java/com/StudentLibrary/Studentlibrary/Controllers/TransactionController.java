package com.StudentLibrary.Studentlibrary.Controllers;

import com.StudentLibrary.Studentlibrary.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam int cardId,
                                            @RequestParam int bookId) throws Exception {
        return transactionService.issueBook(cardId, bookId);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam int cardId,
                                             @RequestParam int bookId) throws Exception {
        return transactionService.returnBook(cardId, bookId);
    }
}
