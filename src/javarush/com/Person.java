package javarush.com;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Person {
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    static class PersonWrapper extends Person {
        private Person person;

        public PersonWrapper(Person person) {
            super(person.getName(), person.getSurname());
            this.person = person;
        }
        public void setName(String name) {
            person.setName(name);
        }
        public void setSurname(String surname) {
            person.setSurname(surname);
        }
        public String getName() {
            return "Что-то добавили к имени " + person.getName();
        }
        public String getSurname() {
            return "Что-то добавили к фамилии " + person.getSurname();
        }
    }

    public static void main(String[] args) {

    }

    public static String toAlternativeString(String string) {
        String str = "";
        for(char c: string.toCharArray()) {
            if(Character.isLowerCase(c)) {
                str = string.toUpperCase();
            } else {
                str = string.toLowerCase();
            }
        }
        return  str;
    }
}


