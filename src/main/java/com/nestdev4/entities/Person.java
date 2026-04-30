package main.java.com.nestdev4.entities;

import java.util.List;

public class Person {
    private String name;
    private String cpf;
    private Integer age;
    private List<Car> cars;

    public Person(String name, String cpf, Integer age, List<Car> cars) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Person: ").append(name)
                .append(" | CPF: ").append(cpf)
                .append(" | Age: ").append(age)
                .append("\nCars:\n");

        for (Car car : cars) {
            sb.append("- ").append(car.getBrand())
                    .append(" ").append(car.getModel())
                    .append(" (").append(car.getPlate()).append(")\n");
        }

        return sb.toString();
    }
}
