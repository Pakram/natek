package com.task.natek.validation.specification;

import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ManagedResource(objectName = "com.task.natek.validation.specification:name=legalEntity")
public class LegalEntitySpecification implements Specification<String> {

    private static Set<String> availableLegalEntities = new CopyOnWriteArraySet<>();

    @ManagedOperation(description = "Show available legal entities")
    public String showAvailableLegalEntities() {
        return availableLegalEntities.toString();
    }

    @ManagedOperation(description = "Add legal entity")
    public boolean addLegalEntity(String legalEntity) {
        return availableLegalEntities.add(legalEntity);
    }

    @Override
    public boolean isSatisfiedBy(String s) {
        return availableLegalEntities.contains(s);
    }

    @PostConstruct
    private void init() {
        availableLegalEntities.add("CS Zurich");
    }
}
