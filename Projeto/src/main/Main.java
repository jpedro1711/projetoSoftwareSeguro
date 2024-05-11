package main;

import controllers.MenuController;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getenv("DB_USER"));
        MenuController menu = new MenuController();
    }
}