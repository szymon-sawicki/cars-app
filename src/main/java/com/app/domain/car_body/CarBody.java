package com.app.domain.car_body;

import com.app.domain.car_body.type.CarBodyColor;
import com.app.domain.car_body.type.CarBodyType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class CarBody {
    CarBodyColor color;
    CarBodyType type;
    List<String> components;
}
