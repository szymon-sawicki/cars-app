package com.app.domain.wheel;

import com.app.domain.wheel.type.TyreType;

import java.util.function.Function;

public interface WheelUtils {
    Function<Wheel,Integer> toWheelSize = wheel -> wheel.size;
    Function<Wheel, TyreType> toTyreType = wheel -> wheel.type;
}
