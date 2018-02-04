package com.task.natek.validation.validator.rules;

import com.task.natek.domain.trade.OptionData;
import com.task.natek.validation.dto.FieldError;
import com.task.natek.validation.specification.AmericanStyleSpecification;
import com.task.natek.validation.specification.CurrencySpecification;
import com.task.natek.validation.specification.ExpireAndPremiumDateSpecification;
import com.task.natek.validation.specification.StyleSpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionRules implements Rule<OptionData> {
    private final AmericanStyleSpecification americanStyleSpecification;
    private final ExpireAndPremiumDateSpecification expireAndPremiumDateSpecification;
    private final StyleSpecification styleSpecification;
    private final CurrencySpecification currencySpecification;

    public OptionRules(AmericanStyleSpecification americanStyleSpecification, ExpireAndPremiumDateSpecification expireAndPremiumDateSpecification, StyleSpecification styleSpecification, CurrencySpecification currencySpecification) {
        this.americanStyleSpecification = americanStyleSpecification;
        this.expireAndPremiumDateSpecification = expireAndPremiumDateSpecification;
        this.styleSpecification = styleSpecification;
        this.currencySpecification = currencySpecification;
    }

    public List<FieldError> checkDataByRules(OptionData optionData) {
        List<FieldError> fieldErrors = new ArrayList<>();

        if (!styleSpecification.isSatisfiedBy(optionData.getStyle())) {
            fieldErrors.add(new FieldError("style", "Style must be american or european"));
        }
        if (!expireAndPremiumDateSpecification.isSatisfiedBy(optionData)) {
            fieldErrors.add(new FieldError("expireAndPremium", "Expire and premium date must be before delivery date"));
        }
        if ("AMERICAN".equals(optionData.getStyle())) {
            if (!americanStyleSpecification.isSatisfiedBy(optionData)) {
                fieldErrors.add(new FieldError("excerciseStartDate", "Excercise start date must be after trade date and before expire date"));
            }
        }

        if (!currencySpecification.isSatisfiedBy(optionData.getPayCcy())) {
            fieldErrors.add(new FieldError("payCcy", "Is not valid ISO currency"));
        }
        if (!currencySpecification.isSatisfiedBy(optionData.getPremiumCcy())) {
            fieldErrors.add(new FieldError("premiumCcy", "Is not valid ISO currency"));
        }

        return fieldErrors;
    }
}
