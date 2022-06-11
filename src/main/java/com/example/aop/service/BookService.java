package com.example.aop.service;

import com.example.aop.model.Books;
import com.example.aop.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;

    public Books saveBooks(Books books){
       return booksRepository.save(books);
    }

    public List<Books> getAllBooks(){
       return booksRepository.findAll();
    }

}
