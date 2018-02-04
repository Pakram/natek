package com.task.natek.validation.validator;

import com.task.natek.domain.trade.OptionData;
import com.task.natek.validation.dto.TradeValidationDTO;
import com.task.natek.validation.validator.rules.AllRules;
import com.task.natek.validation.validator.rules.OptionRules;
import org.springframework.stereotype.Component;

@Component
public class OptionDataValidator implements ProductValidator<OptionData> {
    private final AllRules allRules;
    private final OptionRules optionRules;

    public OptionDataValidator(AllRules allRules, OptionRules optionRules) {
        this.allRules = allRules;
        this.optionRules = optionRules;
    }

    public TradeValidationDTO validate(OptionData optionData) {
        TradeValidationDTO tradeValidationDTO = new TradeValidationDTO(optionData.toString());
        tradeValidationDTO.getFieldErrorList().addAll(allRules.checkDataByRules(optionData));
        tradeValidationDTO.getFieldErrorList().addAll(optionRules.checkDataByRules(optionData));
        return tradeValidationDTO;
    }
}
