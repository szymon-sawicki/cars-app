package domain.car;

import com.app.domain.car.Car;
import com.app.domain.car.exception.CarException;
import com.app.domain.car_body.CarBody;
import com.app.domain.car_body.type.CarBodyType;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor

public class CarTest {

    @Test
    @DisplayName("when car have price in given range")
    public void test1() {

        var givenCar = Car.builder()
                .price(new BigDecimal("200"))
                .build();

        BigDecimal priceFrom = new BigDecimal("100");
        BigDecimal priceTo = new BigDecimal("300");

        assertThat(givenCar.hasPriceInRange(priceFrom, priceTo))
                .isTrue();

    }

    @Test
    @DisplayName("when car have price out of range")
    public void test2() {

        var givenCar = Car.builder()
                .price(new BigDecimal("50"))
                .build();

        BigDecimal priceFrom = new BigDecimal("100");
        BigDecimal priceTo = new BigDecimal("300");

        assertThat(givenCar.hasPriceInRange(priceFrom, priceTo))
                .isFalse();

    }

    @Test
    @DisplayName("when range of price is not correct")
    public void test3() {

        var givenCar = Car.builder()
                .build();

        BigDecimal priceFrom = new BigDecimal("300");
        BigDecimal priceTo = new BigDecimal("200");

        assertThatThrownBy(() -> givenCar.hasPriceInRange(priceFrom, priceTo))
                .isInstanceOf(CarException.class)
                .hasMessageContaining("Incorrect range of price");

    }

    @Test
    @DisplayName("when car body type is null")
    public void test4() {

        var car = Car.builder().build();

        assertThatThrownBy(() -> car.hasBodyType(null))
                .isInstanceOf(CarException.class)
                .hasMessageContaining("car body type is null");


    }

    @Test
    @DisplayName("when car body type is equal")
    public void test5() {

        var car = Car.builder()
                .carBody(CarBody.builder().type(CarBodyType.COMBI).build())
                .build();

        assertThat(car.hasBodyType(CarBodyType.COMBI))
                .isTrue();

    }

    @Test
    @DisplayName("when car body type is not equal")
    public void test6() {

        var car = Car.builder()
                .carBody(CarBody.builder().type(CarBodyType.COMBI).build())
                .build();

        assertThat(car.hasBodyType(CarBodyType.SEDAN))
                .isFalse();

    }

    @Test
    @DisplayName("when list of components is null")
    public void test7() {


        var car = Car.builder().build();

        assertThatThrownBy(() -> car.hasComponents(null))
                .isInstanceOf(CarException.class)
                .hasMessageContaining("list of components is null");


    }

    @Test
    @DisplayName("when equal components list is given")
    public void test8() {

        var car = Car.builder()
                .carBody(CarBody.builder().components(List.of("ABS","TEMPOMAT")).build())
                .build();

        var listOfComponents = List.of("TEMPOMAT","ABS");

        assertThat(car.hasComponents(listOfComponents))
                .isTrue();


    }

    @Test
    @DisplayName("when not equal components list is given")
    public void test() {

        var car = Car.builder()
                .carBody(CarBody.builder().components(List.of("ABS","TEMPOMAT")).build())
                .build();

        var listOfComponents = List.of("ALLOY WHEELS");

        assertThat(car.hasComponents(listOfComponents))
                .isFalse();


    }


}
