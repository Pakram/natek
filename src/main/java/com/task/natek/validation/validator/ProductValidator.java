package com.task.natek.validation.validator;

import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.TradeValidationDTO;

public interface ProductValidator<T extends TradeData> {
    TradeValidationDTO validate(T tradeData);
}
