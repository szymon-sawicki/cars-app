package com.app.domain.car.converter;

import com.app.domain.car.Car;
import com.app.domain.config.converter.JsonConverter;

/**
 * Implementation of converter used to generate and read json data of Car objects
 * @see com.app.domain.car.converter - superclass with logic
 * @author Szymon Sawicki
 */

public class CarJsonConverter extends JsonConverter<Car> {
    public CarJsonConverter(String filename) {
        super(filename);
    }
}
