package org.qsj.springboot1st.controller;

import lombok.extern.slf4j.Slf4j;
import org.qsj.springboot1st.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/***
 * 待完善功能
 * 1，通过邮件发送验证码实现用户级别的注册！
 * 2、认证时admin采用后台预定义数据库，注册用户采用单独自定义数据库 并且区分角色。管理员可操作del add  普通用户只能查看和搜索！
 * 3、打印sql和http header及body到日志文件中
 * 4、密码采用加盐保存 非明文，因此需要用encoder加密保存。读取验证用户的逻辑也要修改！
 */

@Slf4j
@Controller
public class TestController {

    @Autowired
    BookServiceImpl bookServiceImpl;
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("books_count", bookServiceImpl.getBooks().size());
        return "index";
    }

    @RequestMapping("/")
    public String index2(Model model){
        model.addAttribute("books_count", bookServiceImpl.getBooks().size());
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/searchbook")
    public String searchbook(@RequestParam("name") String name,
                             Model model){
        model.addAttribute("booklist", bookServiceImpl.search(name));
        System.out.println(bookServiceImpl.search(name));
        return "books";
    }

    @RequestMapping("/books")
    public String books(Model model){
        model.addAttribute("booklist", bookServiceImpl.getBooks());
//        System.out.println(bookServiceImpl.getBooks());
        return "books";
    }

//    @PreAuthorize("hasRole('admin')")
    @PostMapping("/addbook")
    public String addbook(@RequestParam("name") String name,
                          @RequestParam("author") String author,
                          @RequestParam("price") Double price){
        System.out.println(name+author+price);
        bookServiceImpl.addBook(name,author,price);
        return "redirect:/books";
    }

//    @PreAuthorize("hasRole('admin')")
    @GetMapping("/addbook")
    public String addbook(){
        return "addbook";
    }

//    @PreAuthorize("hasRole('admin')")
    @GetMapping("/delbook")
    public String delbook(@RequestParam("name") String name){
        bookServiceImpl.delBook(name);
        return "redirect:/books";
    }

//    @PreAuthorize("hasRole('admin')")
    @PostMapping("/updatebook")
    public String updatebook(@RequestParam("name") String name,
                             @RequestParam("author") String author,
                             @RequestParam("price") Double price){
        bookServiceImpl.updateBook(name,author,price);
        return "redirect:/books";
    }

//    @PreAuthorize("hasRole('admin')")
    @GetMapping("/updatebook")
    public String updatebook(@RequestParam("name") String name,
                             Model model){
        model.addAttribute("bookname", name);
        return "updatebook";
    }

}