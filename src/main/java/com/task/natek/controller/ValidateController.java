package com.task.natek.controller;

import com.task.natek.domain.trade.TradeData;
import com.task.natek.validation.dto.TradeValidationDTO;
import com.task.natek.validation.validator.TradeDataValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/validate/")
@Slf4j
public class ValidateController {
    private final TradeDataValidator tradeDataValidator;

    public ValidateController(TradeDataValidator tradeDataValidator) {
        this.tradeDataValidator = tradeDataValidator;
    }

    @PostMapping(value = "tradeData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TradeValidationDTO>> validateTradeData(@RequestBody List<TradeData> tradeData) {
        List<TradeValidationDTO> validationDTOList = tradeDataValidator.validate(tradeData);

        if (!validationDTOList.isEmpty()) {
            validationDTOList.forEach(tradeValidationDTO -> {
                log.info(tradeValidationDTO.toString());
                log.info("");
            });
            return ResponseEntity.unprocessableEntity().body(validationDTOList);
        }
        return ResponseEntity.ok().build();


    }
}
