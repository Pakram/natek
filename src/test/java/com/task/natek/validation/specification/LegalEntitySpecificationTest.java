package com.task.natek.validation.specification;

import com.task.natek.NatekApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LegalEntitySpecificationTest extends NatekApplicationTests {
    @Autowired
    LegalEntitySpecification legalEntitySpecification;

    @Test
    public void isSatisfiedBy() {
        Assert.assertEquals(true, legalEntitySpecification.isSatisfiedBy("CS Zurich"));
        Assert.assertEquals(false, legalEntitySpecification.isSatisfiedBy("CS Zuriсh"));
    }
}