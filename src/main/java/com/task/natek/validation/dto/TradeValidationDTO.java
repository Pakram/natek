package com.task.natek.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeValidationDTO implements Serializable {
    private String trade;
    private List<FieldError> fieldErrorList=new ArrayList<>();

    public TradeValidationDTO(String trade) {
        this.trade = trade;
    }
}
