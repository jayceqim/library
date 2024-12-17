package org.qsj.springboot1st.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
public class Account {
    int enable;
    String authority;
    String username;
    String password;
}
