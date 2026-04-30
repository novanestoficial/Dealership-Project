package main.java.com.nestdev4.model.services;

import main.java.com.nestdev4.model.entities.Car;
import main.java.com.nestdev4.model.entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    private List<Person> people = new ArrayList<>();
    private List<Car> cars;

    public PersonService(String path, List<Car> cars) {
        this.cars = cars;
        loadPerson(path);
    }

    private void loadPerson(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {


                String[] parts = line.split(";");

                if (parts.length < 3) {
                    System.out.println("Linha inválida: " + line);
                    continue;
                }

                String name = parts[0];
                String cpf = parts[1];
                int age = Integer.parseInt(parts[2]);

                List<Car> personCars = new ArrayList<>();

                if (parts.length > 3 && !parts[3].isBlank()) {
                    String[] plates = parts[3].split(",");

                    for (String plate : plates) {
                        plate = plate.trim();

                        for (Car car : cars) {
                            if (car.getPlate().equalsIgnoreCase(plate)) {
                                personCars.add(car);
                            }
                        }
                    }
                }

                people.add(new Person(name, cpf, age, personCars));
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Person> getPeople() {
        return people;
    }



    public Person findByCpf(String cpf) {

        if (cpf == null || cpf.isBlank()) {
            return null;
        }

        String inputCpf = cpf.trim();

        for (Person p : people) {

            if (p.getCpf() == null) continue;

            String fileCpf = p.getCpf().trim();

            if (fileCpf.equals(inputCpf)) {
                return p;
            }
        }

        for (Person p : people) {
            System.out.println(p.getCpf());
        }

        return null;
    }



    public Person findOwnerByPlate(String plate) {
        for(Person p : people) {
            for (Car c : p.getCars()) {
                if(c.getPlate().equalsIgnoreCase(plate)){
                    return p;
                }
            }
        }
        return null;
    }

    public void transferCar(String plate, Person from, Person to, String path) {

        if (from == null || to == null) {
            System.out.println("Invalid person.");
            return;
        }

        Car carToTransfer = null;

        for (Car c : from.getCars()) {
            if (c.getPlate().equalsIgnoreCase(plate)) {
                carToTransfer = c;
                break;
            }
        }

        if (carToTransfer != null) {

            from.getCars().remove(carToTransfer);
            to.getCars().add(carToTransfer);

            savePeople(path);

            System.out.println("Transfer successful and file updated!");

        } else {
            System.out.println("Car not found with this owner.");
        }
    }

    public void listPeopleCars() {
        for (Person p : people) {
            System.out.println(p.getName() + " owns:");

            for (Car c : p.getCars()) {
                System.out.println("- " + c.getBrand() + " " + c.getModel());
            }

            System.out.println();
        }
    }

    private void savePeople(String path) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            // cabeçalho
            bw.write("name;cpf;age;plates");
            bw.newLine();

            for (Person p : people) {

                StringBuilder plates = new StringBuilder();

                for (int i = 0; i < p.getCars().size(); i++) {
                    plates.append(p.getCars().get(i).getPlate());

                    if (i < p.getCars().size() - 1) {
                        plates.append(",");
                    }
                }

                bw.write(
                        p.getName() + ";" +
                                p.getCpf() + ";" +
                                p.getAge() + ";" +
                                plates
                );

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void donateCar(String plate, Person from, Person to, String path) {
        transferCar(plate, from, to, path);
    }

    public void sellCar(String plate, Person owner, String path) {

        Car carToRemove = null;

        for (Car c : owner.getCars()) {
            if (c.getPlate().equalsIgnoreCase(plate)) {
                carToRemove = c;
                break;
            }
        }

        if (carToRemove != null) {
            owner.getCars().remove(carToRemove);

            savePeople(path);

            System.out.println("Car sold successfully!");
        } else {
            System.out.println("Car not found.");
        }
    }

    public void tradeCars(String plate1, Person p1, String plate2, Person p2, String path) {

        Car car1 = null;
        Car car2 = null;

        for (Car c : p1.getCars()) {
            if (c.getPlate().equalsIgnoreCase(plate1)) {
                car1 = c;
            }
        }

        for (Car c : p2.getCars()) {
            if (c.getPlate().equalsIgnoreCase(plate2)) {
                car2 = c;
            }
        }

        if (car1 != null && car2 != null) {

            p1.getCars().remove(car1);
            p2.getCars().remove(car2);

            p1.getCars().add(car2);
            p2.getCars().add(car1);

            savePeople(path);

            System.out.println("Trade completed!");

        } else {
            System.out.println("One of the cars was not found.");
        }
    }

    public void listCarsByCpf(String cpf) {
        Person p = findByCpf(cpf);

        if (p == null) {
            System.out.println("Person not found.");
            return;
        }

        System.out.println(p.getName() + " owns:");

        for (Car c : p.getCars()) {
            System.out.println("- " + c.getBrand() + " " + c.getModel());
        }
    }
}
