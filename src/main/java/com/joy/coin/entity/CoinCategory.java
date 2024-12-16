package com.joy.coin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coin_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column(nullable = false, unique = true)
    private String currency;

    @Column(nullable = false)
    private String currencyChineseName;

    @Column(nullable = false)
    private Date createTime;

    private Date updateTime;

    public CoinCategory(String currency, String currencyChineseName, Date createTime){
        this.currency = currency;
        this.currencyChineseName = currencyChineseName;
        this.createTime = createTime;
    }
}
