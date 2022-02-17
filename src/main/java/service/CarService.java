package service;

import model.Car;

import java.util.List;

public interface CarService {
    Car add(Car car);
    List<Car> find(int count);
    List<Car> findAll();
}
