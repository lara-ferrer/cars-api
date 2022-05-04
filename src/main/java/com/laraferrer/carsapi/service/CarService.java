package com.laraferrer.carsapi.service;

import com.laraferrer.carsapi.domain.Car;
import com.laraferrer.carsapi.exception.CarNotFoundException;
import com.laraferrer.carsapi.dto.CarPatchDTO;

import java.util.List;

public interface CarService {
    List<Car> findAllCars();
    Car addCar(Car car);
    List<Car> findCarByBrand(String brand) throws CarNotFoundException;
    Car modifyCar(long carId, Car car) throws CarNotFoundException;
    void patchCar(long carId, CarPatchDTO carPatchDTO) throws CarNotFoundException;
    void deleteCarById(long carId) throws CarNotFoundException;
}
