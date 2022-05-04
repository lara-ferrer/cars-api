package com.laraferrer.carsapi.repository;

import com.laraferrer.carsapi.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findByBrand(String brand);
}
