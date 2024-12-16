package com.joy.coin.controllerTest;

import com.joy.coin.controller.CoinCategoryController;
import com.joy.coin.controller.CoindeskController;
import com.joy.coin.dto.ErrorCodeEnum;
import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
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
        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試5: ");
        System.out.println("coindesk API: " + response.getBody().getCurrentprice());
        System.out.println("==============================");
    }

    @Test
    public void test_transformData(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);

        ResponseEntity<Response> response = coindeskController.transformData();
        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試6:");
        System.out.println("更新時間: " + response.getBody().getUpdateTimeString());
        System.out.println("幣別相關資訊: " + response.getBody().getCurrencyInformation());
        System.out.println("==============================");
    }
}
