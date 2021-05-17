package domain.engine;

import com.app.domain.engine.Engine;
import com.app.domain.engine.EngineUtils;
import com.app.domain.engine.type.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EngineUtilsTest {

    @Test
    @DisplayName("when type is diesel")
    public void test1() {

        var engine = Engine.builder()
                .type(EngineType.DIESEL)
                .build();

        assertThat(EngineUtils.toEngineType.apply(engine))
                .isEqualTo(EngineType.DIESEL);


    }
    @Test
    @DisplayName("when type is diesel")
    public void test2() {

        var engine = Engine.builder()
                .power(300)
                .build();

        assertThat(EngineUtils.toEnginePower.apply(engine))
                .isEqualTo(300);

    }


}
