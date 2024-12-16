package com.joy.coin.controllerTest;

import com.joy.coin.controller.CoinCategoryController;
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
public class CoinCategoryControllerTest {

    @Autowired
    private CoinCategoryController coinCategoryController;


    @Test
    public void test_findByCurrency(){
        coinCategoryController.deleteAll();
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);
        ResponseEntity<Response> findResponse = coinCategoryController.findByCurrency("USD");

        assertEquals(coinCategorys.get(0),findResponse.getBody().getCoinCategory());

        System.out.println("==============================");
        System.out.println("單元測試1: ");
        System.out.println("find 【USD】 coin category: " + findResponse.getBody().getCoinCategory());
        System.out.println("==============================");
    }


    @Test
    public void test_add(){
        CoinCategory coinCategory = new CoinCategory("USD","美金", new Date());
        ResponseEntity<Response> response = coinCategoryController.add(coinCategory);

        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試2-1: ");
        System.out.println("add 【USD】 coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }



    @Test
    public void test_addAll(){
        coinCategoryController.deleteAll();
        Date now = new Date();
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", now),
                new CoinCategory("EUR","歐元", now),
                new CoinCategory("GBP","英鎊", now));
        ResponseEntity<Response> response = coinCategoryController.addAll(coinCategorys);

        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試2-2: ");
        System.out.println("add all coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }

    @Test
    public void test_update(){
        coinCategoryController.deleteAll();
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);
        CoinCategory coinCategory = new CoinCategory("USD","美金_update", new Date());
        ResponseEntity<Response> response = coinCategoryController.update("USD","美金_update");

        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試3: ");
        System.out.println("update coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }

    @Test
    public void test_remove(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);
        CoinCategory mockCoinCategory = new CoinCategory(1L,"USD","美金", new Date(),null);
        ResponseEntity<Response> response = coinCategoryController.delete("USD");

        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試4-1: ");
        System.out.println("remove coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }

    @Test
    public void test_removeAll(){
        ResponseEntity<Response> response = coinCategoryController.deleteAll();

        assertEquals(ErrorCodeEnum.OK.getErrorCode(), response.getBody().getStatusCode());

        System.out.println("==============================");
        System.out.println("單元測試4-2: ");
        System.out.println("remove all coin category");
        System.out.println("==============================");
    }
}
