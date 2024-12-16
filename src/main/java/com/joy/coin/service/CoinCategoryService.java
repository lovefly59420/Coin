package com.joy.coin.service;

import com.joy.coin.dto.ErrorCodeEnum;
import com.joy.coin.dto.MessageEnum;
import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.exception.CoinException;
import com.joy.coin.repository.CoinCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setMessage(MessageEnum.FIND_ALL_SUCCESS.getMessage());
            response.setCoinCategoryList(result);
        }catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.FIND_ALL_ERROR.getMessage());
        }
        return response;
    }

    /**
     *  查詢 By id
     */
    public Response findByCurrency(String currency){
        Response response = new Response();
        try{
            CoinCategory result = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException(MessageEnum.COIN_CATEGORY_NOT_FOUND.getMessage()));
            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setCoinCategory(result);
            response.setMessage(MessageEnum.FIND_BY_SUCCESS.getMessage());
        } catch (CoinException e){
            response.setStatusCode(ErrorCodeEnum.COIN_EXCEPTION.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.FIND_BY_ERROR.getMessage() + e.getMessage());
        }
        return response;
    }

    /**
     *  新增
     */
    public Response saveAll(List<CoinCategory> coinCategorys){
        Response response = new Response();
        try{
            coinCategorys.forEach(coinCategory->coinCategory.setCreateTime(new Date()));
            List<CoinCategory> result = coinCategoryRepository.saveAll(coinCategorys);

            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setCoinCategoryList(result);
            response.setMessage(MessageEnum.SAVE_ALL_SUCCESS.getMessage());
        } catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.SAVE_ALL_ERROR.getMessage() + e.getMessage());
        }
        return response;
    }

    /**
     *  修改
     */
    public Response update(String currency, String currencyChineseName){
        Response response = new Response();
        try{
            CoinCategory oldData = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException(MessageEnum.COIN_CATEGORY_NOT_FOUND.getMessage()));
            if(currencyChineseName != null && !currencyChineseName.isEmpty()){
                oldData.setCurrencyChineseName(currencyChineseName);
            }
            oldData.setUpdateTime(new Date());
            CoinCategory result = coinCategoryRepository.save(oldData);

            response.setCoinCategory(result);
            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setMessage(MessageEnum.UPDATE_SUCCESS.getMessage());
        } catch (CoinException e){
            response.setStatusCode(ErrorCodeEnum.COIN_EXCEPTION.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.UPDATE_ERROR.getMessage() + e.getMessage());
        }
        return response;
    }

    /**
     *  刪除
     */
    public Response remove(String currency){
        Response response = new Response();
        try{
            CoinCategory result = coinCategoryRepository.findByCurrency(currency).orElseThrow(() -> new CoinException(MessageEnum.COIN_CATEGORY_NOT_FOUND.getMessage()));
            coinCategoryRepository.delete(result);

            response.setCoinCategory(result);
            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setMessage(MessageEnum.REMOVE_SUCCESS.getMessage());
        }catch (CoinException e){
            response.setStatusCode(ErrorCodeEnum.COIN_EXCEPTION.getErrorCode());
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.REMOVE_ERROR.getMessage() + e.getMessage());
        }
        return response;
    }

    /**
     *  刪除
     */
    public Response removeAll(){
        Response response = new Response();
        try{
            coinCategoryRepository.deleteAll();

            response.setStatusCode(ErrorCodeEnum.OK.getErrorCode());
            response.setMessage(MessageEnum.REMOVE_SUCCESS.getMessage());
        }catch (Exception e){
            response.setStatusCode(ErrorCodeEnum.EXCEPTION.getErrorCode());
            response.setMessage(MessageEnum.REMOVE_ERROR.getMessage() + e.getMessage());
        }
        return response;
    }
}
