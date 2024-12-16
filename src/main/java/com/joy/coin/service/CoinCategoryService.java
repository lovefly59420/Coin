package com.joy.coin.service;

import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.exception.CoinException;
import com.joy.coin.repository.CoinCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinCategoryService {

    @Autowired
    private CoinCategoryRepository coinCategoryRepository;

    /**
     *  查詢全部
     */
    public Response findAll(){
        Response response = new Response();
        try{
            List<CoinCategory> result = coinCategoryRepository.findAll();

            response.setStatusCode(200);
            response.setMessage("find all coin category success");
            response.setCoinCategoryList(result);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error find all coin category");
        }
        return response;
    }

    /**
     *  查詢 By id
     */
    public Response findByCurrency(String currency){
        Response response = new Response();
        try{
            CoinCategory result = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException("CoinCategory Not Found"));
            response.setStatusCode(200);
            response.setCoinCategory(result);
            response.setMessage("find coin category success");
        } catch (CoinException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error find coin category" + e.getMessage());
        }
        return response;
    }

    /**
     *  新增
     */
    public Response saveAll(List<CoinCategory> coinCategorys){
        Response response = new Response();
        try{
            List<CoinCategory> result = coinCategoryRepository.saveAll(coinCategorys);

            response.setStatusCode(200);
            response.setCoinCategoryList(result);
            response.setMessage("save coin category success");
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error save coin category" + e.getMessage());
        }
        return response;
    }

    /**
     *  修改
     */
    public Response update(String currency, String currencyChineseName){
        Response response = new Response();
        try{
            CoinCategory oldData = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException("CoinCategory Not Found"));
            if(currencyChineseName != null && !currencyChineseName.isEmpty()){
                oldData.setCurrencyChineseName(currencyChineseName);
            }
            CoinCategory result = coinCategoryRepository.save(oldData);

            response.setCoinCategory(result);
            response.setStatusCode(200);
            response.setMessage("update coin category success");
        } catch (CoinException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error save coin category" + e.getMessage());
        }
        return response;
    }

    /**
     *  刪除
     */
    public Response remove(String currency){
        Response response = new Response();
        try{
            CoinCategory result = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException("CoinCategory Not Found"));
            coinCategoryRepository.delete(result);

            response.setCoinCategory(result);
            response.setStatusCode(200);
            response.setMessage("delete coin category success");
        }catch (CoinException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("error delete coin category" + e.getMessage());
        }
        return response;
    }
}
