package com.task.natek.validation.validator.rules;

import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.FieldError;
import com.task.natek.validation.specification.CounterPartySpecification;
import com.task.natek.validation.specification.CurrencySpecification;
import com.task.natek.validation.specification.LegalEntitySpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllRules implements Rule<TradeData> {
    private final CurrencySpecification currencySpecification;
    private final LegalEntitySpecification legalEntitySpecification;
    private final CounterPartySpecification counterPartySpecification;

    public AllRules(CurrencySpecification currencySpecification, LegalEntitySpecification legalEntitySpecification, CounterPartySpecification counterPartySpecification) {
        this.currencySpecification = currencySpecification;
        this.legalEntitySpecification = legalEntitySpecification;
        this.counterPartySpecification = counterPartySpecification;
    }


    public List<FieldError> checkDataByRules(TradeData tradeData) {
        List<FieldError> fieldErrors = new ArrayList<>();

        if (!legalEntitySpecification.isSatisfiedBy(tradeData.getLegalEntity())) {
            fieldErrors.add(new FieldError("legalEntity", "Is not valid legal entity"));
        }
        if (!counterPartySpecification.isSatisfiedBy(tradeData.getCustomer())) {
            fieldErrors.add(new FieldError("counterParty", "Is not valid counterParty"));
        }

        String sellCurrency = tradeData.getCcyPair().substring(0, 3);
        String buyCurrency = tradeData.getCcyPair().substring(3);
        if (!currencySpecification.isSatisfiedBy(sellCurrency)
                || !currencySpecification.isSatisfiedBy(buyCurrency)) {
            fieldErrors.add(new FieldError("ccyPair", "Is not valid ISO currency"));
        }


        return fieldErrors;

    }
}
