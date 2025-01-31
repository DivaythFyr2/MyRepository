package ru.alishev.springcourse.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class Person {
    private int id;

    @NotEmpty(message = "Full name should not be empty")
    @Size(max = 255, message = "Full name must be less than 255 characters")
    private String fullName;

    @Min(value = 1901, message = "Birth year should be greater than 1900")
    private int birthYear;

    private List<Book> books;

    public Person() {

    }

    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person(String fullName, int birthYear, List<Book> books) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
