package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import com.task.natek.domain.trade.OptionData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;



public class ExpireAndPremiumDateSpecificationTest extends NatekApplicationTests {
    @Autowired
    private ExpireAndPremiumDateSpecification expireAndPremiumDateSpecification;

    @Test
    public void isSatisfiedBy() throws ParseException {
        OptionData optionData = new OptionData();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        optionData.setExpiryDate(format.parse("2016-10-09"));
        optionData.setPremiumDate(format.parse("2016-10-09"));
        optionData.setDeliveryDate(format.parse("2016-10-10"));
        Assert.assertEquals(true, expireAndPremiumDateSpecification.isSatisfiedBy(optionData));
        optionData.setExpiryDate(format.parse("2016-10-12"));
        Assert.assertEquals(false, expireAndPremiumDateSpecification.isSatisfiedBy(optionData));

    }
}