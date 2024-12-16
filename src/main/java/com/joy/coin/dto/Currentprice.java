package com.joy.coin.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Currentprice {
    private TimeTemplate time;
    private String disclaimer;
    private String chartName;
    private Map<String, BpiDetail> bpi;

}
