package com.app.domain.wheel;

import com.app.domain.wheel.type.TyreType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class Wheel {
    String model;
    int size;
    TyreType type;
}
