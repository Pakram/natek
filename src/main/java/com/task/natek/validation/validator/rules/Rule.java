package com.task.natek.validation.validator.rules;

import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.FieldError;

import java.util.List;

public interface Rule<T extends TradeData> {
    List<FieldError> checkDataByRules(T tradeData);
}
