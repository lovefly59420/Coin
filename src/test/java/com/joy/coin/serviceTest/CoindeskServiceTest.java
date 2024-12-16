package com.joy.coin.serviceTest;

import com.joy.coin.dto.*;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.repository.CoinCategoryRepository;
import com.joy.coin.service.CoinCategoryService;
import com.joy.coin.service.CoindeskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoindeskServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CoinCategoryRepository coinCategoryRepository;

    @InjectMocks
    private CoindeskService coindeskService;

    @Test
    public void test_getCurrentpriceFromApi(){
        Response mockResponse = new Response();
        Currentprice currentprice = new Currentprice();
        TimeTemplate timeTemplate = new TimeTemplate();
        timeTemplate.setUpdated("Dec 16, 2024 03:00:40 UTC");
        try{
            timeTemplate.setUpdatedISO(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2024-12-16 11:00:40"));
        }catch (Exception e){

        }
        timeTemplate.setUpdateduk("Dec 16, 2024 at 03:00 GMT");
        currentprice.setTime(timeTemplate);
        currentprice.setDisclaimer("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
        currentprice.setChartName("Bitcoin");
        Map<String, BpiDetail> bpiMap = new HashMap<>();
        bpiMap.put("USD",new BpiDetail("USD","&#36;","104,565.286","United States Dollar",104565.2859));
        bpiMap.put("GBP",new BpiDetail("GBP","&pound;","81,819.513","British Pound Sterling",81819.513));
        bpiMap.put("EUR",new BpiDetail("EUR","&euro;","99,289.967","Euro",99289.9672));
        currentprice.setBpi(bpiMap);
        mockResponse.setCurrentprice(currentprice);
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("get coindesk data success");

        when(restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json",String.class)).thenReturn("{\"time\":{\"updated\":\"Dec 16, 2024 03:00:40 UTC\",\"updatedISO\":\"2024-12-16T03:00:40+00:00\",\"updateduk\":\"Dec 16, 2024 at 03:00 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"104,565.286\",\"description\":\"United States Dollar\",\"rate_float\":104565.2859},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"81,819.513\",\"description\":\"British Pound Sterling\",\"rate_float\":81819.513},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"99,289.967\",\"description\":\"Euro\",\"rate_float\":99289.9672}}}");

        Response response = coindeskService.getCoindesk();
        assertEquals(mockResponse,response);
    }

    @Test
    public void test_transformCoindesk(){
        Response mockResponse = new Response();
        Currentprice currentprice = new Currentprice();
        TimeTemplate timeTemplate = new TimeTemplate();
        timeTemplate.setUpdated("Dec 16, 2024 03:00:40 UTC");
        try{
            timeTemplate.setUpdatedISO(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2024/12/16 11:00:40"));
        }catch (Exception e){

        }
        timeTemplate.setUpdateduk("Dec 16, 2024 at 03:00 GMT");
        currentprice.setTime(timeTemplate);
        currentprice.setDisclaimer("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
        currentprice.setChartName("Bitcoin");
        List<CurrencyInformation> currencyInformationList = Arrays.asList(
                new CurrencyInformation("USD","美金","104,565.286"),
                new CurrencyInformation("GBP","歐元","81,819.513"),
                new CurrencyInformation("EUR","英鎊","99,289.967")
        );
        mockResponse.setStatusCode(200);
        mockResponse.setMessage("transform coindesk success");
        mockResponse.setUpdateTimeString("2024/12/16 11:00:40");
        mockResponse.setCurrencyInformation(currencyInformationList);

        CoinCategory USDCoinCategory = new CoinCategory("USD","美金");
        CoinCategory GBPCoinCategory = new CoinCategory("GBP","歐元");
        CoinCategory EURCoinCategory = new CoinCategory("EUR","英鎊");

        when(restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json",String.class)).thenReturn("{\"time\":{\"updated\":\"Dec 16, 2024 03:00:40 UTC\",\"updatedISO\":\"2024-12-16T03:00:40+00:00\",\"updateduk\":\"Dec 16, 2024 at 03:00 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"104,565.286\",\"description\":\"United States Dollar\",\"rate_float\":104565.2859},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"81,819.513\",\"description\":\"British Pound Sterling\",\"rate_float\":81819.513},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"99,289.967\",\"description\":\"Euro\",\"rate_float\":99289.9672}}}");
        when(coinCategoryRepository.findByCurrency("USD")).thenReturn(Optional.of(USDCoinCategory));
        when(coinCategoryRepository.findByCurrency("GBP")).thenReturn(Optional.of(GBPCoinCategory));
        when(coinCategoryRepository.findByCurrency("EUR")).thenReturn(Optional.of(EURCoinCategory));

        Response response = coindeskService.transformCoindesk();
        assertEquals(mockResponse,response);
    }
}
