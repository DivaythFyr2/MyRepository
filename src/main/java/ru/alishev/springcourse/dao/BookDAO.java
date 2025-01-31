package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;


import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Получаем список всех книг
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }

    public List<Book> index() {
        String sql = "SELECT b.id, b.title, b.author, b.year, b.person_id, p.full_name " +
                "FROM Book b LEFT JOIN Person p ON b.person_id = p.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getInt("year"));
            book.setPersonId(rs.getObject("person_id", Integer.class)); // Исправлено
            book.setPersonName(rs.getString("full_name")); // Исправлено
            return book;
        });
    }

    // Получаем книгу по ID
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }

    public Book show(int id) {
        return jdbcTemplate.query(
                "SELECT b.id, b.title, b.author, b.year, b.person_id, p.full_name " +
                        "FROM Book b LEFT JOIN Person p ON b.person_id = p.id WHERE b.id = ?",
                        new Object[]{id},
                        (rs, rowNum) -> {
                            Book book = new Book();
                            book.setId(rs.getInt("id"));
                            book.setTitle(rs.getString("title"));
                            book.setAuthor(rs.getString("author"));
                            book.setYear(rs.getInt("year"));
                            book.setPersonId(rs.getObject("person_id", Integer.class));
                            book.setPersonName(rs.getString("full_name")); // Добавляем имя
                            return book;
                        })
                .stream()
                .findAny()
                .orElse(null);
    }


    public Optional<Book> show(String title) {
        return jdbcTemplate.query("Select * FROM Book WHERE title=?", new Object[]{title}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public List<Book> findBooksByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    // Сохраняем книгу
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assign(int bookId, Integer personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", personId, bookId);
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", bookId);
    }

    public void releaseAllBooksByPersonId(Integer personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE person_id=?", personId);
    }
}

