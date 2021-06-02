package com.app.domain.engine;

import com.app.domain.engine.type.EngineType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

/**
 * domain object that is part of main domain object - Car
 * @see com.app.domain.car.Car
 * @author Szymon Sawicki
 */

public class Engine {
    EngineType type;
    int power;
}
