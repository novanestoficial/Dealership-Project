package main.java.com.nestdev4.services;

import main.java.com.nestdev4.entities.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarService {

        private List<Car> cars = new ArrayList<>();

        public CarService(String path) {
            loadCars(path);
        }

        private void loadCars(String path) {

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {

                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {

                    String[] parts = line.split(";");

                    cars.add(new Car(
                            parts[0],
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3]),
                            parts[4],
                            Double.parseDouble(parts[5])
                    ));
                }

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        public List<Car> getCars() {
            return cars;
        }

}

