package application.service.converter;


import application.extension.CarsServiceConverterExtension;
import com.app.application.converter.CarsServiceConverter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@RequiredArgsConstructor


@ExtendWith(CarsServiceConverterExtension.class)

public class CarsServiceConverterTest {

    private final CarsServiceConverter carsServiceConverter;

    @Test
    @DisplayName("when input file is correct")

    public void test1() {

        var carsFromJson = carsServiceConverter.fromJson().orElseThrow();

        Assertions.assertThat(carsFromJson)
                .isNotEmpty()
                .hasSize(2);


    }




}
