package org.qsj.springboot1st.service;


import org.qsj.springboot1st.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    List<Book> getBooks();
    void delBook(String name);
    void addBook(String name, String author, Double price);
    boolean updateBook(String name, String author, Double price);
    Book search(String name);
}
