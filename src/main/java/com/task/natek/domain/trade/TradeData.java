package com.task.natek.domain.trade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.task.natek.domain.enums.Direction;
import com.task.natek.domain.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpotData.class, name = "Spot"),
        @JsonSubTypes.Type(value = ForwardData.class, name = "Forward"),
        @JsonSubTypes.Type(value = OptionData.class, name = "VanillaOption")
})
public abstract class TradeData {
    private String customer;
    private String ccyPair;
    private Direction direction;
    private Date tradeDate;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal rate;
    private String legalEntity;
    private String trader;
    @JsonProperty(value = "type")
    private ProductType productType;
}
