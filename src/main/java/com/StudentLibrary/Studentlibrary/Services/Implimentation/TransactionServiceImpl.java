package com.StudentLibrary.Studentlibrary.Services.Implimentation;

import com.StudentLibrary.Studentlibrary.Model.*;
import com.StudentLibrary.Studentlibrary.Repositories.BookRepository;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import com.StudentLibrary.Studentlibrary.Repositories.TransactionRepository;
import com.StudentLibrary.Studentlibrary.Services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Value("${books.max_allowed}")
    private int maxAllowedBooks;

    @Value("${books.max_allowed_days}")
    private int maxAllowedDays;

    @Value("${books.fine.per_day}")
    private int finePerDay;

    @Override
    public ResponseEntity<String> issueBook(int cardId, int bookId) throws Exception {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found"));

        if (!book.isAvailable())
            throw new Exception("Book already issued");

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new Exception("Card not found"));

        if (card.getCardStatus() == CardStatus.DEACTIVATED)
            throw new Exception("Card is deactivated");

        if (card.getBooks().size() >= maxAllowedBooks)
            throw new Exception("Book limit reached");

        book.setAvailable(false);
        book.setCard(card);
        card.getBooks().add(book);

        bookRepository.updateBook(book);

        Transaction txn = new Transaction();
        txn.setBook(book);
        txn.setCard(card);
        txn.setIssueOperation(true);
        txn.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        transactionRepository.save(txn);

        return new ResponseEntity<>("Issued successfully. Txn ID: " + txn.getTransactionId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> returnBook(int cardId, int bookId) throws Exception {

        List<Transaction> txns = transactionRepository
                .findByCard_Book(cardId, bookId, TransactionStatus.SUCCESSFUL, true);

        if (txns.isEmpty())
            throw new Exception("No issue record found");

        Transaction lastTxn = txns.get(txns.size() - 1);

        long diffMillis = Math.abs(System.currentTimeMillis() - lastTxn.getTransactionDate().getTime());
        long daysPassed = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        int fine = 0;
        if (daysPassed > maxAllowedDays)
            fine = (int) ((daysPassed - maxAllowedDays) * finePerDay);

        Book book = lastTxn.getBook();
        Card card = lastTxn.getCard();

        book.setAvailable(true);
        book.setCard(null);
        bookRepository.updateBook(book);

        Transaction returnTxn = new Transaction();
        returnTxn.setBook(book);
        returnTxn.setCard(card);
        returnTxn.setFineAmount(fine);
        returnTxn.setIssueOperation(false);
        returnTxn.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        transactionRepository.save(returnTxn);

        return new ResponseEntity<>("Returned successfully. Txn ID: " + returnTxn.getTransactionId(), HttpStatus.OK);
    }
}
