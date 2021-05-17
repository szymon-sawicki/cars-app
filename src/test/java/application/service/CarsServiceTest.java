package application.service;

import application.extension.CarsServiceExtension;
import com.app.application.exception.CarsServiceException;
import com.app.application.service.CarsService;
import com.app.application.service.CarsServiceUtils;
import com.app.application.type.SortItem;
import com.app.domain.car.CarUtils;
import com.app.domain.engine.type.EngineType;
import com.app.domain.wheel.type.TyreType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.app.domain.car.CarUtils.*;
import static com.app.domain.car_body.type.CarBodyType.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(CarsServiceExtension.class)
@RequiredArgsConstructor

public class CarsServiceTest {

    private final CarsService carsService;

    @Test
    @DisplayName("when constructor has correct filename as argument")
    public void test1() {

        assertThat(CarsServiceUtils.toListOfCars.apply(carsService))
                .isNotEmpty()
                .hasSize(3);

    }

    @Test
    @DisplayName("when cars are sorted by number of components")
    public void test2() {

        var sortedListOfNames = carsService.sort(SortItem.COMPONENTS, true).stream()
                .map(toModel::apply)
                .toList();

        var expectedList = List.of("BMW", "RENAULT", "AUDI");

        assertThat(sortedListOfNames)
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(expectedList);

    }

    @Test
    @DisplayName("when cars are sorted by power")
    public void test3() {
        var sortedListOfNames = carsService.sort(SortItem.POWER, true).stream()
                .map(toModel::apply)
                .toList();

        var expectedList = List.of("BMW", "AUDI", "RENAULT");

        assertThat(sortedListOfNames)
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(expectedList);

    }

    @Test
    @DisplayName("when cars are sorted by wheel size")
    public void test4() {

        var sortedListOfNames = carsService.sort(SortItem.WHEEL_SIZE, true).stream()
                .map(toModel::apply)
                .toList();

        var expectedList = List.of("BMW", "RENAULT", "AUDI");

        assertThat(sortedListOfNames)
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(expectedList);


    }

    @Test
    @DisplayName("when sortItem is null")
    public void test5() {

        assertThatThrownBy(() -> carsService.sort(null,true))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("sort item is null");

    }

    @Test
    @DisplayName("when given range of price is not correct")
    public void test6() {

        var priceFrom = new BigDecimal("100");
        var priceTo = new BigDecimal("50");

        assertThatThrownBy(() -> carsService.findCarsWithBodyTypeAndPriceInRange(COMBI,priceFrom,priceTo))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("price is out of range");

    }

    @Test
    @DisplayName("when car body type is null")
    public void test7() {

        var priceFrom = new BigDecimal("50");
        var priceTo = new BigDecimal("100");

        assertThatThrownBy(() -> carsService.findCarsWithBodyTypeAndPriceInRange(null,priceFrom,priceTo))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("car body type is null");

    }


    @Test
    @DisplayName("when car body type is combi and range of price is from 10 to 100")
    public void test8() {

        var priceFrom = new BigDecimal("10");
        var priceTo = new BigDecimal("100");

        var  expected = "RENAULT";

        var result = carsService.findCarsWithBodyTypeAndPriceInRange(COMBI,priceFrom,priceTo).stream()
                .map(toModel::apply)
                .collect(Collectors.toList());

        assertThat(result)
                .isEqualTo(List.of(expected));

    }


    @Test
    @DisplayName("when EngineType is null")
    public void test9() {

        assertThatThrownBy(() -> carsService.findAndSortCarsWithEngineType(null))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("engine type is null");

    }

    @Test
    @DisplayName("when engine type is given")
    public void test10() {

        var carsList = carsService.findAndSortCarsWithEngineType(EngineType.DIESEL).stream()
                .map(toModel::apply)
                .toList();

        var expectedList = List.of("AUDI","RENAULT");

        assertThat(carsList)
                .isEqualTo(expectedList);

    }

    @Test
    @DisplayName("when sort item is null")
    public void test11() {

        assertThatThrownBy(() -> carsService.getStats(null))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("stats item is null");

    }

    @Test
    @DisplayName("when price stats are printed")
    public void test12() {

        assertThat(carsService.priceStats())
                .isEqualTo("MIN: 80. AVERAGE: 116.67. MAX: 150");

    }

    @Test
    @DisplayName("when mileage stats are printed")
    public void test13() {

        assertThat(carsService.mileageStats())
                .isEqualTo("MIN: 10000. AVERAGE: 54000.0. MAX: 140000");

    }

    @Test
    @DisplayName("when engine power stats are printed")
    public void test14() {

        assertThat(carsService.powerStats())
                .isEqualTo("MIN: 110. AVERAGE: 196.0. MAX: 270");

    }

    // TODO jak przetestować tą mapę pod kątem kolejności w tabeli

    @Test
    @DisplayName("when map with cars and kilometers should be returned")
    public void test15() {

        var carsWithMileage = carsService.carsWithMileage();

        var listOfModels = carsWithMileage.entrySet().stream()
                .map(entry -> toModel.apply(entry.getKey()))
                .toList();

        var expectedList = List.of("BMW","AUDI","RENAULT");

        assertThat(listOfModels)
                .isEqualTo(expectedList);

        assertThat(carsService.carsWithMileage())
                .containsValues(12000,10000,140000)
                .isNotEmpty();

    }

    @Test
    @DisplayName("when the map with tyre types and number of cars with that tyre type should be returned")
    public void test16() {

        var mapWithTyreTypes = carsService.tyreTypesWithCount();


        assertThat(mapWithTyreTypes)
                .hasSize(2)
                .containsExactly(entry(TyreType.WINTER,2L),entry(TyreType.SUMMER,1L));

    }

    @Test
    @DisplayName("when list of components is null")
    public void test18() {

        assertThatThrownBy(() -> carsService.findCarsWithGivenComponents(null))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageContaining("list of components is null");

    }

    @Test
    @DisplayName("when list of cars with given components should be returned")
    public void test17() {

        var components = List.of("ABS","TEMPOMAT");

        var listOfCarsWithComponents = carsService.findCarsWithGivenComponents(components).stream()
                .map(toModel::apply)
                .toList();

        var expectedList = List.of("BMW","RENAULT");

        assertThat(listOfCarsWithComponents)
                .isEqualTo(expectedList);

    }







}
