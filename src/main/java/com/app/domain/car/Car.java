package com.app.domain.car;

import com.app.application.exception.CarsServiceException;
import com.app.domain.car.converter.CarJsonConverter;
import com.app.domain.car.exception.CarException;
import com.app.domain.car_body.CarBody;
import com.app.domain.car_body.CarBodyUtils;
import com.app.domain.car_body.type.CarBodyType;
import com.app.domain.config.value_objects.Price;
import com.app.domain.engine.Engine;
import com.app.domain.wheel.Wheel;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor

/**
 * Main domain object. Car contains another domain objects as members - Engine, CarBod and Wheel
 * @author Szymon Sawicki
 */

public class Car {

    String model;
    BigDecimal price;
    int mileage;
    Engine engine;
    CarBody carBody;
    Wheel wheel;

    public Car(String filename) {

        if (Objects.isNull(filename)) {
            throw new CarException("car is null");
        }
        var car = new CarJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarsServiceException("cannot parse json file"));


        this.model = car.model;
        this.price = car.price;
        this.mileage = car.mileage;
        this.engine = car.engine;
        this.carBody = car.carBody;
        this.wheel = car.wheel;
    }

    public boolean hasPriceInRange(BigDecimal priceFrom, BigDecimal priceTo) {

        if (priceFrom.compareTo(priceTo) >= 0) {
            throw new CarException("Incorrect range of price");
        }

        return price.compareTo(priceFrom) >= 0 && price.compareTo(priceTo) <= 0;
    }

    public boolean hasBodyType(CarBodyType carBodyType) {

        if(carBodyType == null) {
            throw new CarException("car body type is null");
        }

        return carBodyType.equals(CarBodyUtils.toType.apply(carBody));
    }

    public boolean hasComponents(List<String> componentsList) {

        if(componentsList == null) {
            throw new CarException("list of components is null");
        }

        return CarUtils.toListOfComponents.apply(this).containsAll(componentsList);

    }

}
