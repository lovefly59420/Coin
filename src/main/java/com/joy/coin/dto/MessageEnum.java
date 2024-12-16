package com.joy.coin.dto;

import lombok.Getter;

public enum MessageEnum {
    COIN_CATEGORY_NOT_FOUND("coin category not found."),

    FIND_ALL_SUCCESS("find all coin category success. "),
    FIND_ALL_ERROR("error find all coin category. "),

    FIND_BY_SUCCESS("find coin category success. "),
    FIND_BY_ERROR("error find coin category. "),

    SAVE_ALL_SUCCESS("save coin category success. "),
    SAVE_ALL_ERROR("error save coin category. "),

    UPDATE_SUCCESS("update coin category success. "),
    UPDATE_ERROR("error update coin category. "),

    REMOVE_SUCCESS("remove coin category success. "),
    REMOVE_ERROR("error remove coin category. "),

    GET_COINDESK_DATA_SUCCESS("get coindesk data success. "),
    GET_COINDESK_DATA_ERROR("error get coindesk data. "),

    TRANSFORM_COINDESK_SUCCESS("transform coindesk success. "),
    TRANSFORM_COINDESK_ERROR("error transform coindesk. "),
    ;

    @Getter
    private String message;


    MessageEnum(String message){
        this.message = message;
    }
}
