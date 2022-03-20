package com.tensquare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Boolean flag;
    private Integer code;
    private String message;
    private Object data;

}
