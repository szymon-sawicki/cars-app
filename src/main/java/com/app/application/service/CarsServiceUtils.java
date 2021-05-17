package com.app.application.service;

import com.app.domain.car.Car;

import java.util.List;
import java.util.function.Function;

public interface CarsServiceUtils {
    Function<CarsService, List<Car>> toListOfCars = carsService -> carsService.cars;
}