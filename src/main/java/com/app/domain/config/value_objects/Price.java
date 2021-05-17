package com.app.domain.config.value_objects;

import com.app.domain.config.value_objects.exception.PriceException;

import java.math.BigDecimal;

public class Price {
    private BigDecimal value;


    public Price() { this.value = BigDecimal.ZERO; }

    public Price(String value) { this.value = init(value); }

    private Price(BigDecimal value) { this.value = value; }

    private BigDecimal init(String valueStr) {
        if (valueStr == null || !valueStr.matches("(\\d+\\.)?\\d+"))
        {
            throw  new PriceException("Price has incorrect value");
        }
        return new BigDecimal(valueStr);
    }
}
