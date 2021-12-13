package com.bhh.entity.dto;

import lombok.Data;

@Data
public class AppResponed<T> {
    //状态码
    private Integer code;
    //信息
    private String msg;
    //pojo
    private T data;
}
