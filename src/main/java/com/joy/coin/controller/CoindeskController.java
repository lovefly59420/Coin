package com.joy.coin.controller;

import com.joy.coin.dto.Response;
import com.joy.coin.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @GetMapping("/get/data")
    public ResponseEntity<Response> getData(){
        Response response = coindeskService.getCoindesk();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/transform/data")
    public ResponseEntity<Response> transformData(){
        Response response = coindeskService.transformCoindesk();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
