package com.task.natek.validation.specification;

import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class CurrencySpecification implements Specification<String> {
    @Override
    public boolean isSatisfiedBy(String currency) {
        try {
            Currency.getInstance(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
