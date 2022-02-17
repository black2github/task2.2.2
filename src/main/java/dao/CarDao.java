package dao;

import model.Car;

import java.util.List;

public interface CarDao {
    List<Car> find(int count);
    List<Car> findAll();
    Car add(Car car);
}
