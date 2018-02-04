package com.task.natek.domain.trade;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class SpotData extends TradeData {
    private Date valueDate;
}
