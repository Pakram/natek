package com.task.natek.validation.specification;

import com.task.natek.domain.trade.ForwardData;
import com.task.natek.domain.trade.SpotData;
import com.task.natek.domain.trade.TradeData;
import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ValueDateBeforeTradeDateSpecification implements Specification<TradeData> {
    @Override
    public boolean isSatisfiedBy(TradeData tradeData) {
        Date valueDate;
        if (tradeData instanceof SpotData) {
            valueDate = ((SpotData) tradeData).getValueDate();
            return valueDate != null && !((SpotData) tradeData).getValueDate().before(tradeData.getTradeDate());
        }
        if (tradeData instanceof ForwardData) {
            valueDate = ((ForwardData) tradeData).getValueDate();
            return valueDate != null && !((ForwardData) tradeData).getValueDate().before(tradeData.getTradeDate());
        }
        return false;
    }
}
