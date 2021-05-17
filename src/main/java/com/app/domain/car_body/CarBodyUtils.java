package com.app.domain.car_body;

import com.app.domain.car_body.type.CarBodyType;

import java.util.List;
import java.util.function.Function;

public interface CarBodyUtils {

    Function<CarBody, CarBodyType> toType = carBody -> carBody.type;

    Function<CarBody, List<String>> toComponents = carBody -> carBody.components;
}
