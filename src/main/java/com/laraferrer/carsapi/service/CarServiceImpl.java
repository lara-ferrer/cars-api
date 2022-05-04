package com.laraferrer.carsapi.service;

import com.laraferrer.carsapi.domain.Car;
import com.laraferrer.carsapi.exception.CarNotFoundException;
import com.laraferrer.carsapi.repository.CarRepository;
import com.laraferrer.carsapi.dto.CarPatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findCarByBrand(String brand) throws CarNotFoundException {
        List<Car> selectedCars = carRepository.findByBrand(brand);
        return selectedCars;
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car modifyCar(long carId, Car car) throws CarNotFoundException {
        Car newCar = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        newCar.setId(car.getId());
        newCar.setBrand(car.getBrand());
        newCar.setModel(car.getModel());
        newCar.setColor(car.getColor());
        newCar.setKilometers(car.getKilometers());
        newCar.setManufacturingDate(car.getManufacturingDate());
        newCar.setPrice(car.getPrice());

        return carRepository.save(newCar);
    }

    @Override
    public void patchCar(long carId, CarPatchDTO carPatchDTO) throws CarNotFoundException {
        Car newCar = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        if (carPatchDTO.getKey().equals("brand")) {
            newCar.setBrand(carPatchDTO.getValue());
        }
        if (carPatchDTO.getKey().equals("model")) {
            newCar.setModel(carPatchDTO.getValue());
        }
        if (carPatchDTO.getKey().equals("color")) {
            newCar.setColor(carPatchDTO.getValue());
        }
        if (carPatchDTO.getKey().equals("kilometers")) {
            newCar.setKilometers(Integer.parseInt(carPatchDTO.getValue()));
        }
        if (carPatchDTO.getKey().equals("manufacturingDate")) {
            newCar.setManufacturingDate(LocalDate.parse(carPatchDTO.getValue()));
        }
        if (carPatchDTO.getKey().equals("price")) {
            newCar.setPrice(Integer.parseInt(carPatchDTO.getValue()));
        }

        carRepository.save(newCar);
    }

    @Override
    public void deleteCarById(long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }
}
