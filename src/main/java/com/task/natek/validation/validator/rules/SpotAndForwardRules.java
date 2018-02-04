package com.task.natek.validation.validator.rules;

import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.FieldError;
import com.task.natek.validation.specification.ValueDateBeforeTradeDateSpecification;
import com.task.natek.validation.specification.ValueDateIsBusinessDaySpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpotAndForwardRules implements Rule<TradeData> {

    private final ValueDateBeforeTradeDateSpecification beforeTradeDateSpecification;
    private final ValueDateIsBusinessDaySpecification isBusinessDaySpecification;

    public SpotAndForwardRules(ValueDateBeforeTradeDateSpecification beforeTradeDateSpecification, ValueDateIsBusinessDaySpecification isBusinessDaySpecification) {
        this.beforeTradeDateSpecification = beforeTradeDateSpecification;
        this.isBusinessDaySpecification = isBusinessDaySpecification;
    }


    public List<FieldError> checkDataByRules(TradeData tradeData) {
        List<FieldError> fieldErrors = new ArrayList<>();

        if (!beforeTradeDateSpecification.isSatisfiedBy(tradeData)) {
            fieldErrors.add(new FieldError("valueDate", "Value date must be before trade date"));
        }
        if (!isBusinessDaySpecification.isSatisfiedBy(tradeData)) {
            fieldErrors.add(new FieldError("valueDate", "Value date must be work date"));
        }

        return fieldErrors;

    }
}
