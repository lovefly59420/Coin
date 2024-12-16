package com.joy.coin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BpiDetail {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private double rate_float;
}
