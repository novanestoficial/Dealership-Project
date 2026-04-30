import main.java.com.nestdev4.entities.Person;
import main.java.com.nestdev4.services.CarService;
import main.java.com.nestdev4.services.PersonService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String peoplePath = "C:\\Users\\David\\Documents\\ESTUDOS-PROGRAMACAO\\CURSO-DE-JAVA\\PROJETO\\CARS-PROJECT\\src\\main\\java\\com\\nestdev4\\Persons";

        CarService carService = new CarService("C:\\Users\\David\\Documents\\ESTUDOS-PROGRAMACAO\\CURSO-DE-JAVA\\PROJETO\\CARS-PROJECT\\src\\main\\java\\com\\nestdev4\\cars");
        PersonService personService = new PersonService(peoplePath, carService.getCars());

        int option;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Sell car");
            System.out.println("2 - Donate car");
            System.out.println("3 - Trade cars");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1: // SELL
                    System.out.print("CPF: ");
                    String cpfSell = sc.nextLine();

                    System.out.print("Plate: ");
                    String plateSell = sc.nextLine();

                    Person owner = personService.findByCpf(cpfSell);

                    personService.sellCar(plateSell, owner, peoplePath);
                    break;

                case 2: // DONATE
                    System.out.print("From CPF: ");
                    String fromCpf = sc.nextLine();

                    System.out.print("To CPF: ");
                    String toCpf = sc.nextLine();

                    System.out.print("Plate: ");
                    String plateDonate = sc.nextLine();

                    Person from = personService.findByCpf(fromCpf);
                    Person to = personService.findByCpf(toCpf);

                    personService.donateCar(plateDonate, from, to, peoplePath);
                    break;

                case 3: // TRADE
                    System.out.print("CPF Person 1: ");
                    String cpf1 = sc.nextLine();

                    System.out.print("Plate 1: ");
                    String plate1 = sc.nextLine();

                    System.out.print("CPF Person 2: ");
                    String cpf2 = sc.nextLine();

                    System.out.print("Plate 2: ");
                    String plate2 = sc.nextLine();

                    Person p1 = personService.findByCpf(cpf1);
                    Person p2 = personService.findByCpf(cpf2);

                    if (p1 == null || p2 == null) {
                        System.out.println("One of the persons was not found.");
                        break;
                    }

                    personService.tradeCars(plate1, p1, plate2, p2, peoplePath);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option");
            }

        } while (option != 4);

        sc.close();
    }
}