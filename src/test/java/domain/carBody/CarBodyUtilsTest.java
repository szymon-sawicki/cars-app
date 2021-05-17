package domain.carBody;

import com.app.domain.car_body.CarBody;
import com.app.domain.car_body.CarBodyUtils;
import com.app.domain.car_body.type.CarBodyType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarBodyUtilsTest {

    @Test
    @DisplayName("when car body type is sedan")
    public void test1() {

        var carBody = CarBody.builder()
                .type(CarBodyType.SEDAN)
                .build();

        assertThat(CarBodyUtils.toType.apply(carBody))
                .isEqualTo(CarBodyType.SEDAN);

    }

    @Test
    @DisplayName("when list of components have elements: ABS, TEMPOMAT")
    public void test2() {

        var carBody = CarBody.builder()
                .components(List.of("ABS","TEMPOMAT"))
                .build();

        assertThat(CarBodyUtils.toComponents.apply(carBody))
                .isNotEmpty()
                .hasSize(2)
                .isEqualTo(List.of("ABS","TEMPOMAT"));

    }


}
