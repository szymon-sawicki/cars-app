package com.app.domain.engine;

import com.app.domain.engine.type.EngineType;

import java.util.function.Function;

/**
 * utility class with functions used to access members of engine domain object and avoid hurting encapsulation
 * @see com.app.domain.engine.Engine
 * @author Szymon Sawicki
 */

public interface EngineUtils {
    Function<Engine, EngineType> toEngineType = engine -> engine.type;
    Function<Engine,Integer> toEnginePower = engine -> engine.power;
}
