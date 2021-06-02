package com.app.application.service.data.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

/**
 * Class to storing set of statistics from collection with given generic type
 * @author Szymon Sawicki
 */

public class Stats<T> {

    private T min;
    private T max;
    private T avg;

    /**
     *
     * @param iss summary statistics object that generate set of integer statistics
     * @return Stats object with double statistics
     */

    public static Stats<Double> toDoubleStats(IntSummaryStatistics iss) {
        return Stats
                .<Double>builder()
                .min((double) iss.getMin())
                .max((double) iss.getMax())
                .avg(iss.getAverage())
                .build();
    }

    /**
     *
     * @param bss summary statistics object that generate set of integer statistics. Class comes from Collections2 library
     * @return Stats object with BigDecimal statistics
     */

    public static Stats<BigDecimal> toBigDecimalStats(BigDecimalSummaryStatistics bss) {
        return Stats
                .<BigDecimal>builder()
                .min(bss.getMin())
                .max(bss.getMax())
                .avg(bss.getAverage())
                .build();
    }
}
