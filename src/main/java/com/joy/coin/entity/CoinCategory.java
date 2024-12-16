package com.joy.coin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coin_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinCategory {

    @Id
    private String currency;

    private String currencyChineseName;
}
