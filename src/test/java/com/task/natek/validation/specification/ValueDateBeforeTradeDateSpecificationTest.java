package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import com.task.natek.domain.trade.ForwardData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValueDateBeforeTradeDateSpecificationTest extends NatekApplicationTests {
    @Autowired
    ValueDateBeforeTradeDateSpecification beforeTradeDateSpecification;

    @Test
    public void isSatisfiedBy() throws ParseException {
        ForwardData forwardData = new ForwardData();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        forwardData.setValueDate(format.parse("2016-10-09"));
        forwardData.setTradeDate(format.parse("2016-10-10"));
        Assert.assertEquals(false, beforeTradeDateSpecification.isSatisfiedBy(forwardData));
        forwardData.setValueDate(format.parse("2016-10-12"));
        Assert.assertEquals(true, beforeTradeDateSpecification.isSatisfiedBy(forwardData));
    }
}