package com.task.natek.validation.validator;

import com.task.natek.domain.trade.ForwardData;
import com.task.natek.domain.trade.OptionData;
import com.task.natek.domain.trade.SpotData;
import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.TradeValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeDataValidator {
    @Autowired
    private ForwardDataValidator forwardDataValidator;
    @Autowired
    private OptionDataValidator optionDataValidator;

    @Autowired
    private SpotDataValidator spotDataValidator;


    @SuppressWarnings("unchecked")
    public List<TradeValidationDTO> validate(Object obj) {
        List<TradeData> tradeDataList = (List<TradeData>) obj;
        List<TradeValidationDTO> validationDTOList = new ArrayList<>();
        for (TradeData tradeData : tradeDataList) {
            TradeValidationDTO validationDTO;
            switch (tradeData.getProductType()) {
                case SPOT:
                    validationDTO = spotDataValidator.validate((SpotData) tradeData);
                    break;
                case FORWARD:
                    validationDTO = forwardDataValidator.validate((ForwardData) tradeData);
                    break;
                case VANILLA_OPTION:
                    validationDTO = optionDataValidator.validate((OptionData) tradeData);
                    break;
                default:
                    continue;
            }
            if (!validationDTO.getFieldErrorList().isEmpty()) {
                validationDTOList.add(validationDTO);
            }
        }
        return validationDTOList;

    }

}
