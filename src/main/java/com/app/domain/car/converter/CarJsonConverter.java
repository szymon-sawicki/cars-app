package com.app.domain.car.converter;

import com.app.domain.car.Car;
import com.app.domain.config.converter.JsonConverter;

public class CarJsonConverter extends JsonConverter<Car> {
    public CarJsonConverter(String filename) {
        super(filename);
    }
}
