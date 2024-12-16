package com.joy.coin.serviceTest;

import com.joy.coin.dto.ErrorCodeEnum;
import com.joy.coin.dto.MessageEnum;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
        mockResponse.setMessage(MessageEnum.FIND_ALL_SUCCESS.getMessage());
        mockResponse.setCoinCategoryList(coinCategorys);

        when(coinCategoryRepository.findAll()).thenReturn(coinCategorys);
        Response response = coinCategoryService.findAll();
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_findByCurrency(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
        mockResponse.setMessage(MessageEnum.FIND_BY_SUCCESS.getMessage());
        mockResponse.setCoinCategory(coinCategorys.get(0));

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategorys.get(0)));
        Response response = coinCategoryService.findByCurrency("USD");
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_saveAll(){
        List<CoinCategory> coinCategorys = Lists.list(
                new CoinCategory("USD","美金", new Date()),
                new CoinCategory("EUR","歐元", new Date()),
                new CoinCategory("GBP","英鎊", new Date()));
        Response mockResponse = new Response();
        mockResponse.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
        mockResponse.setMessage(MessageEnum.SAVE_ALL_SUCCESS.getMessage());
        mockResponse.setCoinCategoryList(coinCategorys);

        when(coinCategoryRepository.saveAll(coinCategorys)).thenReturn(coinCategorys);
        Response response = coinCategoryService.saveAll(coinCategorys);
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_update(){
        CoinCategory coinCategory = new CoinCategory(99L,"USD","美金", new Date(),null);
        CoinCategory updateCoinCategory = new CoinCategory(99L,"USD","美金_update", new Date(),new Date());
        Response mockResponse = new Response();
        mockResponse.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
        mockResponse.setMessage(MessageEnum.UPDATE_SUCCESS.getMessage());
        mockResponse.setCoinCategory(updateCoinCategory);

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategory));
        given(coinCategoryRepository.save(any(CoinCategory.class))).willReturn(updateCoinCategory);
        Response response = coinCategoryService.update("USD","美金_update");
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_remove(){
        CoinCategory coinCategory = new CoinCategory("USD","美金", new Date());
        Response mockResponse = new Response();
        mockResponse.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
        mockResponse.setMessage(MessageEnum.REMOVE_SUCCESS.getMessage());
        mockResponse.setCoinCategory(coinCategory);

        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(coinCategory));
        Response response = coinCategoryService.remove("USD");
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_removeAll(){
        Response response = coinCategoryService.removeAll();
        assertEquals(ErrorCodeEnum.OK.getErrorCode(),response.getStatusCode());
    }
}
