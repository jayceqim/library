package org.qsj.springboot1st.mapper;

import org.apache.ibatis.annotations.*;
import org.qsj.springboot1st.entity.Book;
import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> getBooks();

    @Delete("delete from book where name = #{name}")
    void delBook(@Param("name") String name);

    @Insert("insert into book(name,author,price) values(#{name},#{author},#{price})")
    void addBook(@Param("name") String name,@Param("author") String author,@Param("price") Double price);

    @Update("update book set name=#{name},author=#{author},price=#{price} where name = #{name}")
    boolean updatebook(@Param("name") String name, @Param("author") String author, @Param("price") double price);

    @Select("select * from book where name = #{name}")
    Book serach(@Param("name") String name);
    //#号都是预编译的 无法被SQL攻击利用
}