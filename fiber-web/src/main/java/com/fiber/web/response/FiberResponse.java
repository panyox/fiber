package com.fiber.web.response;

import com.fiber.common.response.ResponseCode;
import lombok.Data;

/**
 * @author panyox
 */
@Data
public class FiberResponse {

    private Integer code;

    private String msg;

    private Object data;

    public FiberResponse() {
    }

    public FiberResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static FiberResponse ok() {
        FiberResponse response = new FiberResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMsg(ResponseCode.SUCCESS.getMessage());
        response.setData(null);
        return response;
    }

    public static FiberResponse ok(Object data) {
        FiberResponse response = new FiberResponse();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMsg(ResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static FiberResponse error() {
        return error(ResponseCode.INNER_ERROR);
    }

    public static FiberResponse error(int code, String msg) {
        FiberResponse response = new FiberResponse();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(null);
        return response;
    }

    public static FiberResponse error(ResponseCode responseCode) {
        return error(responseCode.getCode(), responseCode.getMessage());
    }
}
