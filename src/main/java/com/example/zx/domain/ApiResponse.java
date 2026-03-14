package com.example.zx.domain;

import com.example.zx.enums.StateCodeEnum;
import lombok.Data;

@Data
public class ApiResponse<T> {

    private String code;

    private String msg;

    private T data;

    public static <D> ApiResponse<D> success(D data){
        ApiResponse objectApiResponse = new ApiResponse<>();
        objectApiResponse.setCode(StateCodeEnum.SUCCESS.getCode());
        objectApiResponse.setMsg(StateCodeEnum.SUCCESS.getMsg());
        objectApiResponse.setData(data);
        return objectApiResponse;
    }

    public static <D> ApiResponse<D> fail(D data){
        ApiResponse objectApiResponse = new ApiResponse<>();
        objectApiResponse.setCode(StateCodeEnum.FAIL.getCode());
        objectApiResponse.setMsg(StateCodeEnum.FAIL.getMsg());
        objectApiResponse.setData(data);
        return objectApiResponse;
    }


}
