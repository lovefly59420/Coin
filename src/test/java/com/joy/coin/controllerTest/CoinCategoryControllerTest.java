package com.joy.coin.controllerTest;

import com.joy.coin.controller.CoinCategoryController;
import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CoinCategoryControllerTest {

    @Autowired
    private CoinCategoryController coinCategoryController;


    @Test
    public void test_findByCurrency(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
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
        CoinCategory coinCategory = new CoinCategory("USD","美金");
        ResponseEntity<Response> response = coinCategoryController.add(coinCategory);

        assertEquals(coinCategory, response.getBody().getCoinCategoryList().get(0));

        System.out.println("==============================");
        System.out.println("單元測試2-1: ");
        System.out.println("add 【USD】 coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }



    @Test
    public void test_addAll(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        ResponseEntity<Response> response = coinCategoryController.addAll(coinCategorys);

        assertEquals(coinCategorys, response.getBody().getCoinCategoryList());

        System.out.println("==============================");
        System.out.println("單元測試2-2: ");
        System.out.println("add all coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }

    @Test
    public void test_update(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);
        CoinCategory coinCategory = new CoinCategory("USD","美金_update");
        ResponseEntity<Response> response = coinCategoryController.update("USD","美金_update");

        assertEquals(coinCategory, response.getBody().getCoinCategory());

        System.out.println("==============================");
        System.out.println("單元測試3: ");
        System.out.println("update coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }

    @Test
    public void test_remove(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        ResponseEntity<Response> saveResponse = coinCategoryController.addAll(coinCategorys);
        CoinCategory coinCategory = new CoinCategory("USD","美金");
        ResponseEntity<Response> response = coinCategoryController.delete("USD");

        assertEquals(coinCategory, response.getBody().getCoinCategory());

        System.out.println("==============================");
        System.out.println("單元測試4: ");
        System.out.println("remove coin category: " + response.getBody().getCoinCategory());
        System.out.println("==============================");
    }
}
