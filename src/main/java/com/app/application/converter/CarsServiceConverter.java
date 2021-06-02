package com.app.application.converter;

import com.app.domain.car.Car;
import com.app.domain.config.converter.JsonConverter;

import java.util.List;

/**
 * Implementation of converter used to generate and read json data of list with car objects
 * @see com.app.domain.car.converter - superclass with logic
 * @author Szymon Sawicki
 */

public class CarsServiceConverter extends JsonConverter<List<Car>> {
    public CarsServiceConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
