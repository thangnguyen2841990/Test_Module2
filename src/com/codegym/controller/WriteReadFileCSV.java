package com.codegym.controller;

import com.codegym.controller.phoneBook.PhoneBookManagement;
import com.codegym.model.PersonInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteReadFileCSV {
    private static final String PATH_NAME = "src/data/contacts.csv";

    public void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter(PATH_NAME);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        List<PersonInfo> phoneBooks = PhoneBookManagement.getInstance().getPhoneBooks();
        for (PersonInfo personInfo : phoneBooks) {
            System.out.println(personInfo.toString() + "\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public ArrayList<PersonInfo> readFile(String path) {
        ArrayList<PersonInfo> phoneBooks = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                phoneBooks.add(new PersonInfo(strings[0],
                        strings[1],
                        strings[2],
                        strings[3],
                        strings[4],
                        strings[5]
                ));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return phoneBooks;
    }

}
