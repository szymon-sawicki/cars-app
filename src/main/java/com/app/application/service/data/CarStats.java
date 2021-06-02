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

/**
 * Class used to storing different Stats objects with statistics for mileage, power, price
 * @see com.app.application.service.data.generic.Stats - object used to storing statistics (min, max, average)
 * @author
 */


public class CarStats {
    private Stats<Double> mileage;
    private Stats<Double> power;
    private Stats<BigDecimal> price;
}
