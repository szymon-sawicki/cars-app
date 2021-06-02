package com.app.domain.wheel;

import com.app.domain.wheel.type.TyreType;
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

public class Wheel {
    String model;
    int size;
    TyreType type;
}
