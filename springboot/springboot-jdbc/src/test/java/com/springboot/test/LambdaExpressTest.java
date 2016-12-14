package com.springboot.test;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LambdaExpressTest {

    @Test
    public void test() {
        List<Person> testList = mockPersons();

        System.out.print(testList.stream().filter(Person::isStudent).sorted().map(Person::getName).collect(toList()));

    }

    private List<Person> mockPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Human(true, "1"));
        persons.add(new Human(false, "2"));
        persons.add(new Human(false, "3"));
        persons.add(new Human(true, "4"));
        return persons;
    }

    interface Person {

        boolean isStudent();

        String getName();
    }

    class Human implements Person {

        private boolean isStudent;
        private String name;

        public Human(boolean isStudent, String name) {
            this.isStudent = isStudent;
            this.name = name;
        }

        @Override
        public boolean isStudent() {
            return isStudent;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
