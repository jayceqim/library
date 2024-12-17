package org.qsj.springboot1st.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class Book {
    private String name;
    private String author;
    private Double price;
}
