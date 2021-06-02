package com.app.domain.car_body;

import com.app.domain.car_body.type.CarBodyType;

import java.util.List;
import java.util.function.Function;

/**
 * utility class with functions used to access members of car body domain object and avoid hurting encapsulation
 * @see com.app.domain.car_body.CarBody
 * @author Szymon Sawicki
 */

public interface CarBodyUtils {

    Function<CarBody, CarBodyType> toType = carBody -> carBody.type;

    Function<CarBody, List<String>> toComponents = carBody -> carBody.components;
}
