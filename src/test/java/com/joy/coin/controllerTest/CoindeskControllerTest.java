package com.joy.coin.controllerTest;

import com.joy.coin.controller.CoinCategoryController;
import com.joy.coin.controller.CoindeskController;
import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CoindeskControllerTest {

    @Autowired
    private CoindeskController coindeskController;

    @Autowired
    private CoinCategoryController coinCategoryController;

    @Test
    public void test_getData(){
        ResponseEntity<Response> response = coindeskController.getData();
        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試5: ");
        System.out.println("coindesk API: " + response.getBody().getCurrentprice());
        System.out.println("==============================");
    }

    @Test
    public void test_transformData(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);

        ResponseEntity<Response> response = coindeskController.transformData();
        assertEquals(HttpStatus.OK,response.getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試6:");
        System.out.println("更新時間: " + response.getBody().getUpdateTimeString());
        System.out.println("幣別相關資訊: " + response.getBody().getCurrencyInformation());
        System.out.println("==============================");
    }
}
