package com.task.natek.validation.validator;

import com.task.natek.domain.trade.SpotData;
import com.task.natek.validation.dto.TradeValidationDTO;
import com.task.natek.validation.validator.rules.AllRules;
import com.task.natek.validation.validator.rules.SpotAndForwardRules;
import org.springframework.stereotype.Component;

@Component
public class SpotDataValidator implements ProductValidator<SpotData> {

    private final AllRules allRules;
    private final SpotAndForwardRules spotAndForwardRules;

    public SpotDataValidator(AllRules allRules, SpotAndForwardRules spotAndForwardRules) {
        this.allRules = allRules;
        this.spotAndForwardRules = spotAndForwardRules;
    }

    @Override
    public TradeValidationDTO validate(SpotData spotData) {
        TradeValidationDTO tradeValidationDTO = new TradeValidationDTO(spotData.toString());
        tradeValidationDTO.getFieldErrorList().addAll(allRules.checkDataByRules(spotData));
        tradeValidationDTO.getFieldErrorList().addAll(spotAndForwardRules.checkDataByRules(spotData));
        return tradeValidationDTO;
    }

}
