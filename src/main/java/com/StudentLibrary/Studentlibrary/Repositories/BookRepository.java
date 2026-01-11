package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Modifying
    @Query("update Book b set b.card = :#{#book.card}, b.available = :#{#book.available} where b.id = :#{#book.id}")
    int updateBook(@Param("book") Book book);

    @Query("select b from Book b where b.genre = :genre and b.available = :isAvailable and b.author.name = :author")
    List<Book> findBooksByGenre_Author(@Param("genre") String genre,
                                       @Param("author") String author,
                                       @Param("isAvailable") boolean isAvailable);

    @Query("select b from Book b where b.genre = :genre and b.available = :isAvailable")
    List<Book> findBooksByGenre(@Param("genre") String genre,
                                @Param("isAvailable") boolean isAvailable);

    @Query("select b from Book b where b.available = :isAvailable and b.author.name = :author")
    List<Book> findBooksByAuthor(@Param("author") String author,
                                 @Param("isAvailable") boolean isAvailable);

    @Query("select b from Book b where b.available = :isAvailable")
    List<Book> findBooksByAvailability(@Param("isAvailable") boolean isAvailable);
}
