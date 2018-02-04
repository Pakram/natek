package com.task.natek.validation.validator;

import com.task.natek.domain.trade.ForwardData;
import com.task.natek.validation.dto.TradeValidationDTO;
import com.task.natek.validation.validator.rules.AllRules;
import com.task.natek.validation.validator.rules.SpotAndForwardRules;
import org.springframework.stereotype.Component;

@Component
public class ForwardDataValidator implements ProductValidator<ForwardData> {
    private final AllRules allRules;
    private final SpotAndForwardRules spotAndForwardRules;

    public ForwardDataValidator(AllRules allRules, SpotAndForwardRules spotAndForwardRules) {
        this.allRules = allRules;
        this.spotAndForwardRules = spotAndForwardRules;
    }


    public TradeValidationDTO validate(ForwardData forwardData) {
        TradeValidationDTO tradeValidationDTO = new TradeValidationDTO(forwardData.toString());
        tradeValidationDTO.getFieldErrorList().addAll(allRules.checkDataByRules(forwardData));
        tradeValidationDTO.getFieldErrorList().addAll(spotAndForwardRules.checkDataByRules(forwardData));
        return tradeValidationDTO;
    }
}
