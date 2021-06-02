package com.app.domain.car;

import com.app.domain.config.validator.Validator;
import com.app.domain.wheel.WheelValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Implementation of validator interface used to validate Car objects
 * @see com.app.domain.config.validator.Validator
 * @author Szymon Sawicki
 */

public class CarValidator implements Validator<Car> {
    @Override
    public Map<String, String> validate(Car car) {

        var errors = new HashMap<String, String>();

        if (Objects.isNull(car)) {
            errors.put("car", "is null");
            return errors;
        }

        var wheel = car.wheel;
        var wheelValidator = new WheelValidator();
        var wheelErrors = wheelValidator.validate(wheel);
        errors.putAll(wheelErrors);

        var carBody = car.carBody;
        if(Objects.isNull(carBody)) {
            errors.put("car body","is null");
        }

        var engine = car.engine;
        if(Objects.isNull(engine)) {
            errors.put("engine","is null");
        }

        var model = car.model;
        if (hasIncorrectModel(model)) {
            errors.put("model","has wrong format");
        }

        var price = car.price;

        if(Objects.isNull(price)) {
            errors.put("price","is null");
        } else if(hasIncorrectPrice(price)) {
            errors.put("price","price must be greater than 0");
        }

        var mileage = car.mileage;
        if(mileage <= 0) {
            errors.put("mileage","mileage must be greater than 0");
        }

        return errors;


    }

    private static boolean hasIncorrectPrice(BigDecimal price) { return price.compareTo(BigDecimal.ZERO) < 0; }

    private static boolean hasIncorrectModel(String model) { return model == null || !model.matches("[A-Z//s]+"); }

}
