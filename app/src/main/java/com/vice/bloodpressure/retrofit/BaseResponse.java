package com.vice.bloodpressure.retrofit;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    public int code;
    public String msg;
    public String message;
    public String result;
    public T object;
}

