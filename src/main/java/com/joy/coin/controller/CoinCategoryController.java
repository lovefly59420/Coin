package com.joy.coin.controller;

import com.joy.coin.dto.Response;
import com.joy.coin.entity.CoinCategory;
import com.joy.coin.service.CoinCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinCategoryController {

    @Autowired
    private CoinCategoryService coinCategoryService;

    @PostMapping("/add")
    public ResponseEntity<Response> add(@RequestBody CoinCategory coinCategory){
        List<CoinCategory> saveData = Arrays.asList(coinCategory);
        Response response = coinCategoryService.saveAll(saveData);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/add/all")
    public ResponseEntity<Response> addAll(@RequestBody List<CoinCategory> coinCategory){
        List<CoinCategory> saveData = coinCategory;
        Response response = coinCategoryService.saveAll(saveData);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<Response> update(@RequestParam(value = "currency", required = false) String currency,
                               @RequestParam(value = "currencyChineseName", required = false) String currencyChineseName){
        Response response = coinCategoryService.update(currency, currencyChineseName);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("find/all")
    public ResponseEntity<Response> findAll(){
        Response response = coinCategoryService.findAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/find/{currency}")
    public ResponseEntity<Response> findByCurrency(@PathVariable String currency){
        Response response = coinCategoryService.findByCurrency(currency);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{currency}")
    public ResponseEntity<Response> delete(@PathVariable String currency){
        Response response = coinCategoryService.remove(currency);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Response> deleteAll(){
        Response response = coinCategoryService.removeAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
