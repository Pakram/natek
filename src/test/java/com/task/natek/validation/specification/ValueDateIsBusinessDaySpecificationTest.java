package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import com.task.natek.domain.trade.SpotData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValueDateIsBusinessDaySpecificationTest extends NatekApplicationTests {
    @Autowired
    private ValueDateIsBusinessDaySpecification isBusinessDaySpecification;

    @Test
    public void isSatisfiedBy() throws ParseException {
        SpotData tradeData = new SpotData();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tradeData.setValueDate(format.parse("2016-01-01"));
        tradeData.setCcyPair("USDEUR");
        Assert.assertEquals(false, isBusinessDaySpecification.isSatisfiedBy(tradeData));
        tradeData.setValueDate(format.parse("2016-07-04"));
        Assert.assertEquals(false, isBusinessDaySpecification.isSatisfiedBy(tradeData));
        tradeData.setValueDate(format.parse("2016-01-02"));
        Assert.assertEquals(false, isBusinessDaySpecification.isSatisfiedBy(tradeData));
        tradeData.setValueDate(format.parse("2017-01-02"));
        Assert.assertEquals(true, isBusinessDaySpecification.isSatisfiedBy(tradeData));
    }
}