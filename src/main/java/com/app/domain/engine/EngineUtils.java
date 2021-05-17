package com.app.domain.engine;

import com.app.domain.engine.type.EngineType;

import java.util.function.Function;

public interface EngineUtils {
    Function<Engine, EngineType> toEngineType = engine -> engine.type;
    Function<Engine,Integer> toEnginePower = engine -> engine.power;
}
