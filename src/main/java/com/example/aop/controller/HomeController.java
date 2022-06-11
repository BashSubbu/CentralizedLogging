package com.example.aop.controller;

import com.example.aop.model.Books;
import com.example.aop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class HomeController {
    @Autowired
    private BookService bookService;

    /**
     * to save new book
     * @param books
     * @return
     */
    @PostMapping("/v1/books")
    public ResponseEntity<Object> saveBook(@RequestBody Books books){
        Books booksOutput = null;
        booksOutput = bookService.saveBooks(books);
        if(booksOutput!=null){
            return new ResponseEntity<>("Book saved successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("saving book failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * to get all books
     * @return
     */
    @GetMapping("/v1/books")
    public ResponseEntity<Object> getBooks(){
        List<Books> booksList = null;
        booksList = bookService.getAllBooks();

        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }


}
