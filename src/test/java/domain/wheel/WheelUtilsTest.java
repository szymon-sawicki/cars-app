package domain.wheel;

import com.app.domain.wheel.Wheel;
import com.app.domain.wheel.WheelUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WheelUtilsTest {

    @Test
    @DisplayName("when size is 10")
    public void test1() {

        var wheel = Wheel.builder()
                .size(10)
                .build();

        assertThat(WheelUtils.toWheelSize.apply(wheel))
                .isEqualTo(10);


    }




}
