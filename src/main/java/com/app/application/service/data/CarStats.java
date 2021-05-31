package com.app.application.service.data;

import com.app.application.service.data.generic.Stats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarStats {
    private Stats<Double> mileage;
    private Stats<Double> power;
    private Stats<BigDecimal> price;
}
