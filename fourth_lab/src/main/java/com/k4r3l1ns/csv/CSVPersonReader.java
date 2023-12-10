package com.k4r3l1ns.csv;


import com.k4r3l1ns.model.Department;
import com.k4r3l1ns.model.Person;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVPersonReader {

    public static List<Person> read(String csvFilePath, Character separator) {

        List<Person> personList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        boolean flag = false;
        try (InputStream in = new FileInputStream(csvFilePath);
             CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(separator)
                             .build())
                     .build()
        ) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                if (!flag) {
                    flag = true;
                    continue;
                }

                Person person = new Person(
                        Integer.parseInt(nextLine[0]),
                        String.valueOf(nextLine[1]),
                        Person.Gender.resolveByName(String.valueOf(nextLine[2])),
                        new Department(String.valueOf(nextLine[4])),
                        Integer.parseInt(nextLine[5]),
                        formatter.parse(nextLine[3])
                );
                personList.add(person);

            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }
}
