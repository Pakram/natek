package com.task.natek.domain.enums;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductType {
    @JsonProperty("Spot")
    SPOT,
    @JsonProperty("Forward")
    FORWARD,
    @JsonProperty("VanillaOption")
    VANILLA_OPTION


}
