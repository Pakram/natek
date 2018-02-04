package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CurrencySpecificationTest extends NatekApplicationTests{
    @Autowired
    CurrencySpecification currencySpecification;

    @Test
    public void isSatisfiedBy() {
        Assert.assertEquals(false,currencySpecification.isSatisfiedBy("abc"));
        Assert.assertEquals(true,currencySpecification.isSatisfiedBy("USD"));

    }
}