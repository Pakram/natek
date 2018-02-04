package com.task.natek.validation.specification;

import com.task.natek.domain.trade.ForwardData;
import com.task.natek.domain.trade.SpotData;
import com.task.natek.domain.trade.TradeData;
import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

@Component
public class ValueDateIsBusinessDaySpecification implements Specification<TradeData> {
    @Override
    public boolean isSatisfiedBy(TradeData tradeData) {
        Date valueDate = null;
        if (tradeData instanceof SpotData) {
            valueDate = ((SpotData) tradeData).getValueDate();
        }
        if (tradeData instanceof ForwardData) {
            valueDate = ((ForwardData) tradeData).getValueDate();
        }
        if (valueDate == null) return false;
        Currency currency;
        try {
            currency = Currency.getInstance(tradeData.getCcyPair().substring(0, 3));
        } catch (IllegalArgumentException e) {
            return true;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(valueDate);

        // check if weekend
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }

        // check if New Year's Day
        if (cal.get(Calendar.MONTH) == Calendar.JANUARY
                && cal.get(Calendar.DAY_OF_MONTH) == 1) {
            return false;
        }

        // check if Christmas
        if (cal.get(Calendar.MONTH) == Calendar.DECEMBER
                && cal.get(Calendar.DAY_OF_MONTH) == 25) {
            return false;
        }
        // check President's Day (3rd Monday of February)
        if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                && currency.getCurrencyCode().equals("USD")) {
            return true;
        }

        // check if 4th of July
        if (cal.get(Calendar.MONTH) == Calendar.JULY
                && cal.get(Calendar.DAY_OF_MONTH) == 4
                && currency.getCurrencyCode().equals("USD")) {
            return false;
        }

        // check Thanksgiving (4th Thursday of November)
        if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
                && currency.getCurrencyCode().equals("USD")) {
            return false;
        }

        // check Memorial Day (last Monday of May)
        if (cal.get(Calendar.MONTH) == Calendar.MAY
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                && cal.get(Calendar.DAY_OF_MONTH) > (31 - 7)
                && currency.getCurrencyCode().equals("USD")) {
            return false;
        }
        return true;
    }
}
