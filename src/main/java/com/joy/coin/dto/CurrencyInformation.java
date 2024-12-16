package com.joy.coin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CurrencyInformation {
    private String currency;
    private String currencyChineseName;
    private String rate;

    public CurrencyInformation(){
        this.currencyChineseName = "DB No Data";
    }

}
