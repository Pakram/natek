package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StyleSpecificationTest extends NatekApplicationTests{
    @Autowired
    private StyleSpecification styleSpecification;

    @Test
    public void isSatisfiedBy() {
        Assert.assertEquals(true,styleSpecification.isSatisfiedBy("AMERICAN"));
        Assert.assertEquals(true,styleSpecification.isSatisfiedBy("EUROPEAN"));
        Assert.assertEquals(false,styleSpecification.isSatisfiedBy("RUSSIAN"));
    }
}