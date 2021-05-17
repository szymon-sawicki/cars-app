package domain.carBody;

import com.app.domain.car.Car;
import com.app.domain.car.CarValidator;
import com.app.domain.car_body.CarBody;
import com.app.domain.config.validator.Validator;
import com.app.domain.config.validator.ValidatorException;
import com.app.domain.engine.Engine;
import com.app.domain.wheel.Wheel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.assertj.core.api.Assertions.*;

public class CarValidatorTest {

    @Test
    @DisplayName("when car is null")
    public void test1() {

        var carValidator = new CarValidator();

        assertThatThrownBy(() -> Validator.validate(carValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("car", "is null");

    }


    @Test
    @DisplayName("when car body is null")
    public void test2() {

        var carValidator = new CarValidator();

        var car = Car.builder()
                .build();


        assertThatThrownBy(() -> Validator.validate(carValidator, car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("car body: is null");

    }

    @Test
    @DisplayName("when model has incorrect format")
    public void test3() {

        var carValidator = new CarValidator();

        var car = Car.builder()
                .model("mazda")
                .build();

        assertThatThrownBy(() -> Validator.validate(carValidator,car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("model: has wrong format");

    }

    @Test
    @DisplayName("when car is correct")
    public void test4() {

        var carValidator = new CarValidator();

        var engine = Engine.builder().build();
        var carBody = CarBody.builder().build();
        var wheel = Wheel.builder().build();

        var car = Car.builder()
                .model("MAZDA")
                .mileage(500)
                .price(new BigDecimal("200"))
                .engine(engine)
                .wheel(wheel)
                .carBody(carBody)
                .build();

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> Validator.validate(carValidator,car));

    }

}
