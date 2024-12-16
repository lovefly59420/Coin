package com.joy.coin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.coin.entity.CoinCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int statusCode;

    private String message;

    private CoinCategory coinCategory;

    private List<CoinCategory> coinCategoryList;

    private Currentprice currentprice;

    private String updateTimeString;

    private List<CurrencyInformation> currencyInformation;

}
