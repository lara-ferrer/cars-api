package com.laraferrer.carsapi.controller;

import com.laraferrer.carsapi.domain.Car;
import com.laraferrer.carsapi.dto.CarPatchDTO;
import com.laraferrer.carsapi.service.CarService;
import com.laraferrer.carsapi.exception.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/car")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = null;
        cars = carService.findAllCars();

        return ResponseEntity.ok(cars);
    }

    @PostMapping(value = "/car")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping(value = "/car/{carId}")
    public ResponseEntity<Car> modifyCar(@PathVariable long carId, @RequestBody Car car) throws CarNotFoundException {
        Car newCar = carService.modifyCar(carId, car);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }

    @PatchMapping(value = "/car/{carId}")
    public ResponseEntity<Void> patchCar(@PathVariable long carId, @RequestBody CarPatchDTO restaurantPatchDTO) throws CarNotFoundException {
        carService.patchCar(carId, restaurantPatchDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/car/{carId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable long carId) throws CarNotFoundException {
        carService.deleteCarById(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}