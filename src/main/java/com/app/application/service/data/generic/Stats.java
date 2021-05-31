package com.app.application.service.data.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.IntSummaryStatistics;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stats <T>{
    private T min;
    private T max;
    private T avg;

    public static Stats<Double> toDoubleStats(IntSummaryStatistics iss) {
        return Stats
                .<Double>builder()
                .min((double)iss.getMin())
                .max((double)iss.getMax())
                .avg(iss.getAverage())
                .build();
    }
}
