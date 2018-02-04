package com.task.natek.validation.specification;

import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ManagedResource(objectName = "com.task.natek.validation.specification:name=style")
public class StyleSpecification implements Specification<String> {

    private static Set<String> availableStyle = new CopyOnWriteArraySet<>();

    @ManagedOperation(description = "Show available style")
    public String showStyle() {
        return availableStyle.toString();
    }

    @ManagedOperation(description = "Add legal style")
    public boolean addStyle(String style) {
        return availableStyle.add(style);
    }

    @Override
    public boolean isSatisfiedBy(String s) {
        return availableStyle.contains(s);
    }

    @PostConstruct
    private void init()
    {
        availableStyle.add("AMERICAN");
        availableStyle.add("EUROPEAN");
    }
}
