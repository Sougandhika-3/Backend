package com.ebook.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ebook.entites.Books;
import com.ebook.exception.ResourceNotFoundException;
import com.ebook.repository.BookRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void deleteBook_ValidId_ShouldDeleteBook() {
        // Arrange
        int bookId = 1;
        Books book = new Books();
        book.setId(bookId);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(book));

        // Act
        bookService.deleteBook(bookId);

        // Assert
        Mockito.verify(bookRepository, Mockito.times(1)).delete(book);
    }

    @Test
    public void deleteBook_InvalidId_ShouldThrowResourceNotFoundException() {
        // Arrange
        int bookId = 1;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.empty());

        // Act and assert
        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(bookId);
        });
    }
}

