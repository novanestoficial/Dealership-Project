package main.java;

import main.java.com.nestdev4.model.entities.Person;
import main.java.com.nestdev4.model.services.CarService;
import main.java.com.nestdev4.model.services.PersonService;
import main.java.com.nestdev4.model.services.StartService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StartService start = new StartService();
        start.startService();
    }
}