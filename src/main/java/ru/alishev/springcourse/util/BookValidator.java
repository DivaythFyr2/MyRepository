package ru.alishev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.models.Book;

import java.util.Optional;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        // нужно посмотреть, есть ли книга с таким же названием в БД

        Optional<Book> existingBook = bookDAO.show(book.getTitle());

        if (existingBook.isPresent() && existingBook.get().getId() != book.getId()) {
            errors.rejectValue("title", "", "A book with this title already exists");
        }

    }
}

