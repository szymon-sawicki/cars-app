package com.app.domain.car;

import com.app.domain.car_body.CarBodyUtils;
import com.app.domain.car_body.type.CarBodyType;
import com.app.domain.engine.EngineUtils;
import com.app.domain.engine.type.EngineType;
import com.app.domain.wheel.WheelUtils;
import com.app.domain.wheel.type.TyreType;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Utility class used to achieve encapsulation
 * @author Szymon Sawicki
 */

public interface CarUtils {

    Function<Car, CarBodyType> toCarBodyType = car -> CarBodyUtils.toType.apply(car.carBody);
    Function<Car, List<String>> toListOfComponents = car -> CarBodyUtils.toComponents.apply(car.carBody);

    ToIntFunction<Car> toEnginePower = car -> EngineUtils.toEnginePower.apply(car.engine);
    Function<Car, EngineType> toEngineType = car -> EngineUtils.toEngineType.apply(car.engine);

    Function<Car, Integer> toWheelSize = car -> WheelUtils.toWheelSize.apply(car.wheel);
    Function<Car, TyreType> toTyreType = car -> WheelUtils.toTyreType.apply(car.wheel);

    Function<Car, String> toModel = car -> car.model;
    Function<Car, BigDecimal> toPrice = car -> car.price;
      ToIntFunction<Car> toMileage = car -> car.mileage;

    Comparator<Car> compareByNumberOfComponents = Comparator.comparing(car -> CarUtils.toListOfComponents.apply(car).size());
    Comparator<Car> compareByPower = Comparator.comparing(CarUtils.toEnginePower::applyAsInt);
    Comparator<Car> compareByMileage = Comparator.comparing(CarUtils.toMileage::applyAsInt);
    Comparator<Car> compareByWheelSize = Comparator.comparing(CarUtils.toWheelSize::apply);
    Comparator<Car> compareByModel = Comparator.comparing(CarUtils.toModel::apply);






}
