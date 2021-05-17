package application.service.converter;

import application.extension.CarsServiceConverterWithErrorExtension;
import com.app.application.converter.CarsServiceConverter;
import com.app.application.service.CarsService;
import com.app.domain.config.converter.JsonConverterException;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(CarsServiceConverterWithErrorExtension.class)
@RequiredArgsConstructor

public class CarsServiceConverterWithErrorTest {

    private final CarsServiceConverter carsServiceConverter;


    @Test
    @DisplayName("when filename is not correct")
    public void test1() {

        assertThatThrownBy(() -> carsServiceConverter.fromJson().orElseThrow())
                .isInstanceOf(JsonConverterException.class);

    }


}


