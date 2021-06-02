package com.app.domain.wheel;

import com.app.domain.wheel.type.TyreType;

import java.util.function.Function;

/**
 * utility class with functions used to access members of wheel domain object and avoid hurting encapsulation
 * @see com.app.domain.wheel.Wheel
 * @author Szymon Sawicki
 */

public interface WheelUtils {
    Function<Wheel,Integer> toWheelSize = wheel -> wheel.size;
    Function<Wheel, TyreType> toTyreType = wheel -> wheel.type;
}
