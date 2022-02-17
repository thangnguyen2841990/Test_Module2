package com.codegym.controller.phoneBook;

import com.codegym.controller.GeneralManagement;
import com.codegym.model.PersonInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IPhoneBookManagement extends GeneralManagement<PersonInfo>{
int findPersonByPhoneNumber(String phoneNumber);
    int findPersonByName(String name);
    void writeToFiles() throws IOException;
    void readFiles() throws IOException;
}
