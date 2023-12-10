package com.k4r3l1ns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
public class Person {

    private final int id;
    private final String name;
    private final Gender gender;
    private final Department department;
    private final int salary;
    private final Date birthday;

    public void print() {

        System.out.println("Person [id = " + id + "]\n" +
                "Name: " + name + "\n" +
                "Gender: " + gender.getName() + "\n" +
                "Department: " + department.getName() + "\n" +
                "Salary: " + salary + "\n" +
                "Birthday = " + birthday + "\n"
        );
    }

    @Getter
    public enum Gender {

        MALE("MALE"),
        WITHDRAWAL("FEMALE");

        private final static Map<String, Gender> _map;

        static {

            _map = Stream.of(values()).collect(Collectors.toMap(Gender::getName, Function.identity()));
        }

        private final String name;

        Gender(String name) {
            this.name = name;
        }

        public static Gender resolveByName(String name) {

            return _map.getOrDefault(name.toUpperCase(), null);
        }
    }
}
