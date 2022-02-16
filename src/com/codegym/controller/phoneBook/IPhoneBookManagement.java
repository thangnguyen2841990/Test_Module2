package com.codegym.controller.phoneBook;

import com.codegym.controller.GeneralManagement;
import com.codegym.model.PersonInfo;

import java.util.ArrayList;

public interface IPhoneBookManagement extends GeneralManagement<PersonInfo>{
int findPersonByPhoneNumber(String phoneNumber);
    int findPersonByName(String name);

}
