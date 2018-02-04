package com.task.natek.validation.specification;

import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ManagedResource(objectName="com.task.natek.validation.specification:name=counterParty")
public class CounterPartySpecification implements Specification<String> {

    private static Set<String> availableCounterParties = new CopyOnWriteArraySet<>();

    @ManagedOperation(description = "Show available counterParties")
    public String showAvailableCounterParties() {
        return availableCounterParties.toString();
    }

    @ManagedOperation(description = "Add counter party")
    public boolean addCounterParty(String counterParty) {
        return availableCounterParties.add(counterParty);
    }

    @Override
    public boolean isSatisfiedBy(String s) {
        return availableCounterParties.contains(s);
    }

    @PostConstruct
    private void init() {
        availableCounterParties.add("PLUTO1");
        availableCounterParties.add("PLUTO2");
    }
}
