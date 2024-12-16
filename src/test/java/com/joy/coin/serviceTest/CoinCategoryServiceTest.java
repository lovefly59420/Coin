package com.joy.coin.serviceTest;

import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.repository.CoinCategoryRepository;
import com.joy.coin.service.CoinCategoryService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoinCategoryServiceTest {

    @Mock
    private CoinCategoryRepository coinCategoryRepository;

    @InjectMocks
    private CoinCategoryService coinCategoryService;


    @Test
    public void test_findAll(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("find all coin category success");
        mockResponse.setCoinCategoryList(coinCategorys);

        when(coinCategoryRepository.findAll()).thenReturn(coinCategorys);
        Response response = coinCategoryService.findAll();
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_findByCurrency(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("find coin category success");
        mockResponse.setCoinCategory(coinCategorys.get(0));

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategorys.get(0)));
        Response response = coinCategoryService.findByCurrency("USD");
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_saveAll(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金"),
                new CoinCategory("EUR","歐元"),
                new CoinCategory("GBP","英鎊"));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("save coin category success");
        mockResponse.setCoinCategoryList(coinCategorys);

        when(coinCategoryRepository.saveAll(coinCategorys)).thenReturn(coinCategorys);
        Response response = coinCategoryService.saveAll(coinCategorys);
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_update(){
        CoinCategory coinCategory = new CoinCategory("USD","美金");
        CoinCategory updateCoinCategory = new CoinCategory("USD","美金_update");
        Response mockResponse = new Response();
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("update coin category success");
        mockResponse.setCoinCategory(updateCoinCategory);

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategory));
        when(coinCategoryRepository.save(updateCoinCategory)).thenReturn(updateCoinCategory);
        Response response = coinCategoryService.update("USD","美金_update");
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_remove(){
        CoinCategory coinCategory = new CoinCategory("USD","美金");
        Response mockResponse = new Response();
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("delete coin category success");
        mockResponse.setCoinCategory(coinCategory);

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategory));
        Response response = coinCategoryService.remove("USD");
        assertEquals(mockResponse,response);
    }




}
