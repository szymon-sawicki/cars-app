package com.app.domain.engine;

import com.app.domain.engine.type.EngineType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class Engine {
    EngineType type;
    int power;
}
