package com.codegym.view;

import com.codegym.controller.phoneBook.IPhoneBookManagement;
import com.codegym.controller.phoneBook.PhoneBookManagement;
import com.codegym.model.PersonInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        IPhoneBookManagement phoneBookManagement = PhoneBookManagement.getInstance();
        PhoneBookManagement phoneBookManagement1 = PhoneBookManagement.getInstance();
        try {
            phoneBookManagement1.readFiles("contacts.txt");
        } catch (IOException e) {

        }
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 7) {
                System.err.println("Menu chỉ có từ 1 - 7!");
            }
            switch (choice) {
                case 1: {
                    doDisplayAllPhoneBook(phoneBookManagement);
                    break;
                }
                case 2: {
                    doAddNewPhoneBook(phoneBookManagement);
                    break;
                }
                case 3: {
                    doUpdatePhoneBook(phoneBookManagement);
                    break;
                }
                case 4: {
                    doRemovePhoneBook(phoneBookManagement);
                    break;
                }
                case 5: {
                    doFindPhoneBook(phoneBookManagement);
                    break;
                }
                case 6: {
                    try {
                        phoneBookManagement1.readFiles("contacts.csv");
                    } catch (IOException e) {
                    }
                    break;
                }
                case 7: {
                    try {
                        phoneBookManagement1.writeToFiles("contacts.csv");
                    } catch (IOException e) {

                    }
                    break;
                }
            }
            try {
                phoneBookManagement1.writeToFiles("contacts.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private void doFindPhoneBook(IPhoneBookManagement phoneBookManagement) {
        int choiceFind = -1;
        do {
            System.out.println("1. Tìm theo Số điện thoại.");
            System.out.println("2. Tìm theo tên.");
            System.out.println("0. Quay lại");
            System.out.println("Nhập lụa chọn của bạn: ");
            choiceFind = inputNumber.nextInt();
            if (choiceFind > 2 ){
                System.err.println("MENU chỉ có 1 và 2!");
            }
            switch (choiceFind){
                case 1 :{
                    System.out.println("Nhập số điện thoại: ");
                    String phoneNumber = inputString.nextLine();
                    int index = phoneBookManagement.findPersonByPhoneNumber(phoneNumber);
                    if (index == -1) {
                        System.err.println("SĐT nhập vào không đúng");
                    } else {
                        phoneBookManagement.getByIndex(index);
                    }
                    break;
                }
                case 2 :{
                    System.out.println("Nhập họ và tên: ");
                    String name = inputString.nextLine();
                    int index = phoneBookManagement.findPersonByName(name);
                    if (index == -1) {
                        System.err.println("Tên nhập vào không tồn tại!");
                    } else {
                        phoneBookManagement.getByIndex(index);
                    }
                    break;
                }
            }
            break;
        }while (choiceFind != 0);
    }

    private void doRemovePhoneBook(IPhoneBookManagement phoneBookManagement) {
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        int index = phoneBookManagement.findPersonByPhoneNumber(phoneNumber);
        if (index == -1) {
            System.err.println("SĐT nhập vào không đúng");
        } else {
            phoneBookManagement.remove(index);
            System.out.println("Xóa thành công!");
        }
    }

    private void doUpdatePhoneBook(IPhoneBookManagement phoneBookManagement) {
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        int index = phoneBookManagement.findPersonByPhoneNumber(phoneNumber);
        if (index == -1) {
            System.err.println("SĐT nhập vào không đúng");
        } else {
            PersonInfo newPersonInfo = inputNewPhoneBookInfo();
            phoneBookManagement.update(index, newPersonInfo);
            System.out.println("Cập nhật thành công!");
        }
    }

    private void doAddNewPhoneBook(IPhoneBookManagement phoneBookManagement) {
        PersonInfo newPersonInfo = inputNewPhoneBookInfo();
        phoneBookManagement.add(newPersonInfo);
        System.out.println("Thêm thành công!");
    }

    private void menu() {
        System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
        System.out.println("1. Xem danh sách.");
        System.out.println("2. Thêm mới.");
        System.out.println("3. Cập nhật.");
        System.out.println("4. Xóa.");
        System.out.println("5. Tìm kiếm.");
        System.out.println("6. Đọc từ file.");
        System.out.println("7. Ghi vào file.");
        System.out.println("0. Thoát.");
    }

    private PersonInfo inputNewPhoneBookInfo() {
        System.out.println("Nhập thôn tin danh bạ");
        System.out.println("Họ và tên: ");
        String name = inputString.nextLine();
        System.out.println("Số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        System.out.println("Nhóm: ");
        String group = inputString.nextLine();
        System.out.println("Giới tính: ");
        String sex = inputString.nextLine();
        System.out.println("Email: ");
        String email = inputEmail();
        System.out.println("Địa chỉ: ");
        String address = inputString.nextLine();
        return new PersonInfo(name, group, phoneNumber, sex, email, address);
    }

    private void doDisplayAllPhoneBook(IPhoneBookManagement phoneBookManagement) {
        System.out.println("----Xem danh sách----");
        int size = phoneBookManagement.getSize();
        if (size == 0) {
            System.err.println("Danh bạ rỗng!");
        } else {
            phoneBookManagement.displayAll();
        }
    }

    private boolean checkRegex(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private String inputPhoneNumber() {
        while (true) {
            System.out.println("Nhập số điện thoại theo dạng(84) - (0xxxxxxxxx)");
            String phoneNumber = inputString.nextLine();
            final String REGEX_PHONENUMBER = "^\\(84\\-\\(0[0-9]{9}\\))$";
            boolean isCheckPhoneNumber = checkRegex(REGEX_PHONENUMBER, phoneNumber);
            if (isCheckPhoneNumber) {
                System.out.println("Số điện thoại hợp lệ!");
                return phoneNumber;
            } else {
                System.err.println("Nhập số điện thoại theo dạng(84) - (0xxxxxxxxx)");
            }
        }
    }

    private String inputEmail() {
        while (true) {
            System.out.println("Nhập email theo dạng(thangnguyen@gmail.com)");
            String email = inputString.nextLine();
            final String REGEX_EMAIL = "^\\w+@\\w+(\\.\\w+){1,2}$";
            boolean isCheckEmail = checkRegex(REGEX_EMAIL, email);
            if (isCheckEmail) {
                System.out.println("Email hợp lệ!");
                return email;
            } else {
                System.err.println("Nhập email theo dạng(thangnguyen@gmail.com)");
            }
        }
    }
}
