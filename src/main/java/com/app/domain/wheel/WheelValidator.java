package com.app.domain.wheel;

import com.app.domain.config.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WheelValidator implements Validator<Wheel> {

    @Override
    public Map<String, String> validate(Wheel wheel) {

        var errors = new HashMap<String,String>();

        if(Objects.isNull(wheel)) {
            errors.put("wheel","is null");
            return errors;
        }

        if(Objects.isNull(wheel.model)) {
            errors.put("wheel model","is null");
        }

        else if(!wheel.model.matches("[A-Z ]+")) {
            errors.put("wheel model","has wrong format");
        }

        if(wheel.size <=0) {
            errors.put("wheel size", "size is out of range");
        }

        if(Objects.isNull(wheel.type)) {
            errors.put("wheel type","wheel type is null");
        }

        return errors;

    }
}
