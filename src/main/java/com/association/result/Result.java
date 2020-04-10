package com.association.result;


/**
 * @description 统一 API响应结果封装
 * @memo 控制Result权限，构建结果Result对象统一使用com.javalsj.blog.vo.ResultFactory工厂类来创建
 */
public class Result {
    /**
     * 响应状态
     */
    private int status;
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private Object data;

    Result(int code, int status, String message, Object data) {
        this.status=status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {  return status;}

    public void setStatus(int status) {this.status = status; }

    //初始化一个新建的Result对象，表示空消息
    public Result() {
    }

    public static Result success(Object data) {
        return buidResult(ResultCode.SUCCESS,ResultStatus.SUCCESS, "成功", data);
    }

    public static Result error(String message) {
        return buidResult(ResultCode.FAIL, ResultStatus.FAIL, message, null);
    }

    public static Result buidResult(ResultCode resultCode,ResultStatus resultStatus, String message, Object data) {
        return buidResult(resultCode.code,resultStatus.status, message, data);
    }

    public static Result buidResult(int code,int resultStatus, String message, Object data) {
        return new Result(code,resultStatus, message, data);
    }

}