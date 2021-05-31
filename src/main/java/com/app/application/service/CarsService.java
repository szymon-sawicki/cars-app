package com.app.application.service;



import com.app.application.converter.CarsServiceConverter;
import com.app.application.exception.CarsServiceException;
import com.app.application.service.data.CarStats;
import com.app.application.service.data.generic.Stats;
import com.app.application.type.SortItem;
import com.app.application.type.StatsItem;
import com.app.domain.car.Car;
import com.app.domain.car.CarUtils;
import com.app.domain.car_body.type.CarBodyType;
import com.app.domain.config.converter.JsonConverterException;
import com.app.domain.engine.type.EngineType;
import com.app.domain.wheel.WheelUtils;
import com.app.domain.wheel.type.TyreType;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.application.type.SortItem.COMPONENTS;
import static com.app.domain.car.CarUtils.*;
import static java.util.Map.Entry.comparingByValue;

@RequiredArgsConstructor

/**
 * Main service class used to  manage collection of cars loaded from jsonfile
 * @author Szymon Sawicki
 */

public class CarsService {

    /**
     * filepath to json file
     */

    private String filename;

    /**
     * list of cars loaded from jsonfile
     */

    List<Car> cars;


    public CarsService(String filename) {

        this.filename = filename;
        init();

    }

    /**
     * initialization method, load car data (JSON) from given file and converts it to List
     */

    private void init() {
        if (Objects.isNull(filename)) {
            throw new CarsServiceException("filename cannot be null");
        }
        this.cars = new CarsServiceConverter(filename)
                .fromJson()
                .orElseThrow(() -> new JsonConverterException("cannot parse json file"));
    }

    /**
     * @param sortItem   type of element that is compared
     * @param descending direction of sorting
     * @return list of sorted cars
     */

    public List<Car> sort(SortItem sortItem, boolean descending) {

        if (sortItem == null) {
            throw new CarsServiceException("sort item is null");
        }

        var carsStream = switch (sortItem) {

            case COMPONENTS -> cars.stream().sorted(compareByNumberOfComponents);
            case POWER -> cars.stream().sorted(compareByPower);
            case WHEEL_SIZE -> cars.stream().sorted(compareByWheelSize);

        };

        var carsList = carsStream.collect(Collectors.toList());

        if (descending) {

            Collections.reverse(carsList);

        }

        return carsList;

    }

    /**
     * @param carBodyType only cars with this type will be displayed
     * @param priceFrom   range of price
     * @param priceTo     range of price
     * @return list of cars with given body type and price in range
     */

    public List<Car> findCarsWithBodyTypeAndPriceInRange(CarBodyType carBodyType, BigDecimal priceFrom, BigDecimal priceTo) {

        if (carBodyType == null) {
            throw new CarsServiceException("car body type is null");
        }

        if (priceFrom.compareTo(priceTo) >= 0) {
            throw new CarsServiceException("price is out of range");
        }

        return cars.stream()
                .filter(car -> car.hasBodyType(carBodyType) && car.hasPriceInRange(priceFrom, priceTo))
                .toList();

    }

    /**
     * @param engineType only cars with this type will be displayed
     * @return sorted (by model) list with cars that have given type of engine
     */


    public List<Car> findAndSortCarsWithEngineType(EngineType engineType) {

        if (engineType == null) {
            throw new CarsServiceException("engine type is null");
        }

        return cars.stream()
                .filter(car -> toEngineType.apply(car).equals(engineType))
                .sorted(compareByModel)
                .toList();

    }

    //TODO jak fajnie zwrócić statystyki ?  Osobny obiekt ?

    public CarStats getStats() {

        var priceStats = cars
                .stream()
                .collect(Collectors2.summarizingBigDecimal(toPrice));

        var mileageStats = cars
                .stream()
                .collect(Collectors.summarizingInt(toMileage));

        var powerStats = cars
                .stream()
                .collect(Collectors.summarizingInt(toEnginePower));

        return CarStats
                .builder()
                .price(Stats.<BigDecimal>builder().min().max().avg().build())
                .power(Stats.toDoubleStats(powerStats))
                .build();
    }





    /**
     * @return map with car as key and their mileage as value
     */

    public Map<Car, Integer> carsWithMileage() {

        return cars.stream()
                .sorted(compareByMileage)
                .collect(
                        LinkedHashMap::new,
                        (map, car) -> map.put(car, toMileage.applyAsInt(car)),
                        Map::putAll);

    }

    /**
     * @return map with tyre type as key and number of cars with tyres of that type as value
     */

    public Map<TyreType, Long> tyreTypesWithCount() {

        var result = cars.stream().collect(Collectors.groupingBy(
                toTyreType, LinkedHashMap::new, Collectors.counting()));

        return result.entrySet().stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new
                ));

    }

    /**
     *
     * @param components list of components
     * @return list with cars that contains those components
     */

    public List<Car> findCarsWithGivenComponents(List<String> components) {

        if(components == null) {
            throw new CarsServiceException("list of components is null");
        }

       return cars.stream()
                .filter(car -> car.hasComponents(components))
                .sorted(compareByModel)
                .toList();


    }

}


