package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CounterPartySpecificationTest extends NatekApplicationTests {
    @Autowired
    CounterPartySpecification counterPartySpecification;

    @Test
    public void isSatisfiedBy() {
        Assert.assertEquals(true, counterPartySpecification.isSatisfiedBy("PLUTO1"));
        Assert.assertEquals(true, counterPartySpecification.isSatisfiedBy("PLUTO2"));
        Assert.assertEquals(false, counterPartySpecification.isSatisfiedBy("ABC"));
    }
}