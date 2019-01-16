package com.eduedu.chanpin.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/3/2.
 */

@Setter
@Getter
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static Result success(String message) {

        return new Result(true, message);
    }

    public static <T> Result<T> success(String message, T data) {

        return new Result(true, message, data);
    }

    public static Result fail(String message) {

        return new Result(false, message);
    }

    public static <T> Result<T> fail(String message, T data) {

        return new Result(false, message, data);
    }
}
