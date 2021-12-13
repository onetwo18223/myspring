package com.bhh.entity.dto;

import lombok.Data;

@Data
public class Result<T> {
    //编码
    private Integer code;
    //信息
    private String msg;
    //pojo
    private T data;
}
