package hiber.service;

import hiber.model.Car;

public interface CarService {
    void add(Car car);
    Car getCar(long id);
}
