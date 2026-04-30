package main.java.com.nestdev4.model.entities;

import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private String plate;
    private Double mileage;
    private String name;
    private Double price;

    public Car() {
    }


    public Car(String brand, String model, String plate, Double mileage, String name, Double price) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.mileage = mileage;
        this.name = name;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(plate, car.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plate);
    }

    @Override
    public String toString() {
        return "INFORMATIONS OF THE CAR: " +
                "\nBRAND: " + brand
                + "\nMODEL: " + model
                + "\nPLATE: " + plate
                + "\nMILEAGE: " + mileage
                + "\nNAME: " + name
                + "\nPRICE: " + price + '\n';
    }
}
