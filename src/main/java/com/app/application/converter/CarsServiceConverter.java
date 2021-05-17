package com.app.application.converter;

import com.app.domain.car.Car;
import com.app.domain.config.converter.JsonConverter;

import java.util.List;

public class CarsServiceConverter extends JsonConverter<List<Car>> {
    public CarsServiceConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
