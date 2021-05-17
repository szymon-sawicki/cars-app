package domain.engine;

import com.app.domain.config.validator.Validator;
import com.app.domain.config.validator.ValidatorException;
import com.app.domain.engine.Engine;
import com.app.domain.engine.EngineValidator;
import com.app.domain.engine.type.EngineType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EngineValidatorTest {

    @Test
    @DisplayName("when engine is null")
    public void test1() {

        var engineValidator = new EngineValidator();

        assertThatThrownBy(() -> Validator.validate(engineValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("engine: is null");


    }

    @Test
    @DisplayName("when type is null")
    public void test2() {

        var engineValidator = new EngineValidator();

        var engine = Engine.builder()
                .build();

        assertThatThrownBy(() -> Validator.validate(engineValidator, engine))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("engine type: is null");

    }

    @Test
    @DisplayName("when power is out of range")
    public void test3() {

        var engineValidator = new EngineValidator();

        var engine = Engine.builder()
                .power(-20)
                .build();

        assertThatThrownBy(() -> Validator.validate(engineValidator, engine))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("engine power: is out of range");

    }

    @Test
    @DisplayName("when engine is correct")
    public void test4() {

        var engineValidator = new EngineValidator();

        var engine = Engine.builder()
                .power(100)
                .type(EngineType.DIESEL)
                .build();

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> Validator.validate(engineValidator,engine));

    }

}
