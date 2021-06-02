package com.app.domain.engine;

import com.app.domain.config.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Implementation of validator interface used to validate Engine objects
 * @see com.app.domain.config.validator.Validator
 * @author Szymon Sawicki
 */

public class EngineValidator implements Validator<Engine> {

    @Override
    public Map<String, String> validate(Engine engine) {

        var errors = new HashMap<String, String>();

        if (Objects.isNull(engine)) {
            errors.put("engine", "is null");
            return errors;
        }


        var type = engine.type;
        if (Objects.isNull(type)) {
            errors.put("engine type", "is null");
        }

        var power = engine.power;
        if (power < 0) {
            errors.put("engine power", "is out of range");
        }


        return errors;
    }
}
