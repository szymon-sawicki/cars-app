package domain.wheel;

import com.app.domain.car.Car;
import com.app.domain.car.CarValidator;
import com.app.domain.config.validator.Validator;
import com.app.domain.config.validator.ValidatorException;
import com.app.domain.wheel.Wheel;
import com.app.domain.wheel.WheelValidator;
import com.app.domain.wheel.type.TyreType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WheelValidatorTest {


    @Test
    @DisplayName("when model has incorrect format")
    public void test1() {

        var wheelValidator = new WheelValidator();

        var wheel = Wheel.builder()
                .model("bridgestone")
                .build();

        assertThatThrownBy(() -> Validator.validate(wheelValidator, wheel))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("wheel model: has wrong format");

    }

    @Test
    @DisplayName("when wheel is null")
    public void test2() {

        var wheelValidator = new WheelValidator();

        assertThatThrownBy(() -> Validator.validate(wheelValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("wheel: is null");

    }

    @Test
    @DisplayName("when size is lower than 0")
    public void test3() {

        var wheelValidator = new WheelValidator();

        var wheel = Wheel.builder()
                .size(-10)
                .build();

        assertThatThrownBy(() -> Validator.validate(wheelValidator, wheel))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("wheel size: size is out of range");

    }

    @Test
    @DisplayName("when model is null")
    public void test4() {

        var wheelValidator = new WheelValidator();

        var wheel = Wheel.builder()
                .build();

        assertThatThrownBy(() -> Validator.validate(wheelValidator, wheel))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("wheel model: is null");

    }

    @Test
    @DisplayName("when wheel is correct")
    public void test5() {

        var wheelValidator = new WheelValidator();

        var wheel = Wheel.builder()
                .model("BRIDGESTONE")
                .type(TyreType.SUMMER)
                .size(10)
                .build();

        Assertions.assertDoesNotThrow(() -> Validator.validate(wheelValidator,wheel));

    }



}
