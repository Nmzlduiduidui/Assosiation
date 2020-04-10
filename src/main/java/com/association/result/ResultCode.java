package com.association.result;


/**
 * @description 响应码枚举，参考 HTTP状态码的语义
 * @memo 无备注说明
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(0);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }

}