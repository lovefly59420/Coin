package com.joy.coin.service;

import com.google.gson.Gson;
import com.joy.coin.dto.BpiDetail;
import com.joy.coin.dto.CurrencyInformation;
import com.joy.coin.dto.Currentprice;
import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.repository.CoinCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoindeskService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CoinCategoryRepository coinCategoryRepository;

    public Response getCoindesk() {
        Response response = new Response();
        try{
            Currentprice currentprice = this.getCurrentpriceFromApi();

            response.setCurrentprice(currentprice);
            response.setStatusCode(200);
            response.setMessage("get coindesk data success");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error get coindesk data");
        }
        return response;
    }


    public Response transformCoindesk(){
        Response response = new Response();
        try{
            Currentprice currentprice = this.getCurrentpriceFromApi();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String dateStr = sdf.format(currentprice.getTime().getUpdatedISO());

            response.setUpdateTimeString(dateStr);
            List<CurrencyInformation> CurrencyInformations = new ArrayList<>();

            for(String key:currentprice.getBpi().keySet()){
                BpiDetail bpiDetail = currentprice.getBpi().get(key);
                Optional<CoinCategory> optionalCoinCategory = coinCategoryRepository.findByCurrency(key);
                CurrencyInformation currencyInformation = new CurrencyInformation();
                if(optionalCoinCategory.isPresent()){
                    CoinCategory coinCategory = optionalCoinCategory.get();
                    currencyInformation.setCurrencyChineseName(coinCategory.getCurrencyChineseName());
                }
                currencyInformation.setCurrency(bpiDetail.getCode());
                currencyInformation.setRate(bpiDetail.getRate());
                CurrencyInformations.add(currencyInformation);
            }
            response.setCurrencyInformation(CurrencyInformations);
            response.setStatusCode(200);
            response.setMessage("transform coindesk success");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error transform coindesk" + e.getMessage());
        }
        return response;
    }


    private Currentprice getCurrentpriceFromApi(){
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        String dataJson = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        Currentprice currentprice = gson.fromJson(dataJson, Currentprice.class);
        return currentprice;
    }


}
