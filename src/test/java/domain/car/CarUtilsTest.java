package domain.car;

import com.app.domain.car.Car;
import com.app.domain.car.CarUtils;
import com.app.domain.car_body.CarBody;
import com.app.domain.car_body.type.CarBodyType;
import com.app.domain.engine.Engine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarUtilsTest {

    @Test
    @DisplayName("when cars are sorted by power")
    public void test4() {

        var car1 = Car.builder()
                .model("MAZDA")
                .engine(Engine.builder().power(150).build())
                .build();

        var car2 = Car.builder()
                .model("HONDA")
                .engine(Engine.builder().power(90).build())
                .build();

        var car3 = Car.builder()
                .model("NISSAN")
                .engine(Engine.builder().power(120).build())
                .build();

        var sortedListOfCars = List.of(car1, car2, car3).stream()
                .sorted(CarUtils.compareByPower)
                .toList();

        var expectedList = List.of(car2,car3,car1);

        assertThat(sortedListOfCars)
                .isEqualTo(expectedList);

    }


    @Test
    @DisplayName("when BodyType is combi")
    public void test1() {

        var car = Car.builder()
                .carBody(CarBody.builder().type(CarBodyType.COMBI).build())
                .build();

        assertThat(CarUtils.toCarBodyType.apply(car))
                .isEqualTo(CarBodyType.COMBI);

    }

    @Test
    @DisplayName("when price is ten")
    public void test2() {

        var car = Car.builder()
                .price(BigDecimal.TEN)
                .build();

        assertThat(CarUtils.toPrice.apply(car))
                .isEqualTo(BigDecimal.TEN);

    }

    @Test
    @DisplayName("when mileage is 1000")
    public void test3() {

        var car = Car.builder()
                .mileage(1000)
                .build();

        assertThat(CarUtils.toMileage.applyAsInt(car))
                .isEqualTo(1000);

    }


}
