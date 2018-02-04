package com.task.natek.validation.specification;

import com.task.natek.domain.trade.OptionData;
import io.github.uetoyo.patterns.specification.Specification;
import org.springframework.stereotype.Component;

@Component
public class ExpireAndPremiumDateSpecification implements Specification<OptionData> {
    @Override
    public boolean isSatisfiedBy(OptionData optionData) {
        return optionData.getExpiryDate().before(optionData.getDeliveryDate())
                && optionData.getPremiumDate().before(optionData.getDeliveryDate());

    }
}
