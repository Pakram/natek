package com.task.natek.validation.specification;

import com.task.natek.domain.trade.OptionData;
import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public class AmericanStyleSpecification implements Specification<OptionData> {
    @Override
    public boolean isSatisfiedBy(OptionData tradeData) {
        return tradeData.getExcerciseStartDate().after(tradeData.getTradeDate())
                && tradeData.getExcerciseStartDate().before(tradeData.getExpiryDate());
    }
}
