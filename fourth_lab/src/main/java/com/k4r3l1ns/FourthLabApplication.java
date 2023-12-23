package com.k4r3l1ns;


import com.k4r3l1ns.csv.CSVPersonReader;

public class FourthLabApplication {

    public static final String CSV_FILE_PATH =
            "/home/k4r3l1ns/Desktop/java_labs/fourth_lab/src/main/resources/foreign_names.csv";

    public static final Character SEPARATOR = ';';

    public static void main(String[] args) {

        var personList = CSVPersonReader.read(CSV_FILE_PATH, SEPARATOR);

        personList.forEach(person -> {
            person.print();
            System.out.println();
        });
    }
}