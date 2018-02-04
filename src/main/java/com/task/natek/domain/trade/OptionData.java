package com.task.natek.domain.trade;


import com.task.natek.domain.enums.Strategy;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class OptionData extends TradeData {
    private String style;
    private Strategy strategy;
    private Date deliveryDate;
    private Date expiryDate;
    private String payCcy;
    private BigDecimal premium;
    private String premiumCcy;
    private String premiumType;
    private Date premiumDate;
    private Date excerciseStartDate;
}
