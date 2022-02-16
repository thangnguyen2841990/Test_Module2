package com.codegym.controller.phoneBook;

import com.codegym.model.PersonInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookManagement implements IPhoneBookManagement {
    private PhoneBookManagement() {

    }

    private static final PhoneBookManagement INSTANCE = new PhoneBookManagement();

    public static PhoneBookManagement getInstance() {
        return INSTANCE;
    }

    public List<PersonInfo> getPhoneBooks() {
        return phoneBooks;
    }

    private List<PersonInfo> phoneBooks = new ArrayList<>();
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER_PHONEBOOK = "Số điện thoại,Nhóm,Họ tên, Giới tính, Địa chỉ, Email";
    private static final String fileNamePhoneBook = "src/data/contacts.csv";

    @Override
    public void add(PersonInfo personInfo) {
        phoneBooks.add(personInfo);

    }

    @Override
    public void update(int index, PersonInfo personInfo) {
        phoneBooks.set(index, personInfo);

    }

    @Override
    public void remove(int index) {
        phoneBooks.remove(index);

    }

    @Override
    public void displayAll() {
        for (PersonInfo phoneBook : phoneBooks) {
            System.out.println(phoneBook);
        }
    }

    @Override
    public PersonInfo getByIndex(int index) {
        return phoneBooks.get(index);
    }

    @Override
    public int getSize() {
        return phoneBooks.size();
    }


    @Override
    public int findPersonByPhoneNumber(String phoneNumber) {
        int index = -1;
        for (int i = 0; i < phoneBooks.size(); i++) {
            if (phoneBooks.get(i).getPhoneNumber().equals(phoneNumber)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int findPersonByName(String name) {
        int index = -1;
        for (int i = 0; i < phoneBooks.size(); i++) {
            if (phoneBooks.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }


        public void writeToFiles(String path) throws IOException {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (PersonInfo personInfo: phoneBooks) {
                bufferedWriter.write(personInfo.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }
    public void readFiles(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String[] lines = line.split(",");
            String name = lines[0].trim();
            String group = lines[1].trim();
            String phoneNumber = lines[2].trim();
            String sex = lines[3].trim();
            String address = lines[4].trim();
            String email = lines[5].trim();
        PersonInfo personInfo = new PersonInfo(name,group,phoneNumber,sex,email,address);
        this.phoneBooks.add(personInfo);
        }
        bufferedReader.close();
        fileReader.close();
    }


}
