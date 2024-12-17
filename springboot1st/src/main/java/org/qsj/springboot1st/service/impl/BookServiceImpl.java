package org.qsj.springboot1st.service.impl;

import org.qsj.springboot1st.entity.Book;
import org.qsj.springboot1st.mapper.BookMapper;
import org.qsj.springboot1st.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper search;

    @Override
    public List<Book> getBooks() {
        List<Book> books = search.getBooks();
        return books;
    }

    @Override
    public void delBook(String name) {
        search.delBook(name);
    }

    @Override
    public void addBook(String name, String author, Double price) {
            search.addBook(name, author, price);
    }

    @Override
    public boolean updateBook(String name, String author, Double price) {
            boolean a = search.updatebook(name, author, price);
        return a;
    }

    @Override
    public Book search(String name) {
        Book book = search.serach(name);
        return book;
    }
}
