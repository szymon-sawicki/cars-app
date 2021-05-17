package com.app.domain.car_body;

import com.app.domain.config.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class CarBodyValidator implements Validator<CarBody> {

    @Override
    public Map<String, String> validate(CarBody carBody) {

        var errors = new HashMap<String, String>();

        if (carBody.color == null) {
            errors.put("car body color", "is null");
        }

        if (carBody.components == null || carBody.components.isEmpty()) {
            errors.put("car body components", "is null or empty");
        }

        if (carBody.components != null && carBody.components
                .stream()
                .filter(a -> !a.matches("[A-Z ]+"))
                .toList().size() > 0) {
            errors.put("car body components", "car body components can contains only upper cases and white spaces");
        }

        return errors;

    }
}