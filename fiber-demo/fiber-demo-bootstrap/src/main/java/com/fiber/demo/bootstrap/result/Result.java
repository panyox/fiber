package com.fiber.demo.bootstrap.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class Result {
    private Boolean success;
    private String error;
    private Object data;

    public static Result ok() {
        Result res = new Result();
        res.setSuccess(true);
        res.setError(null);
        res.setData(null);
        return res;
    }

    public static Result ok(Object data) {
        Result res = new Result();
        res.setSuccess(true);
        res.setError(null);
        res.setData(data);
        return res;
    }

    public static Result error(String error) {
        Result res = new Result();
        res.setSuccess(false);
        res.setError(error);
        res.setData(null);
        return res;
    }
}
