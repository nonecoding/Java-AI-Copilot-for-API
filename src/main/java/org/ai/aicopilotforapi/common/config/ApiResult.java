package org.ai.aicopilotforapi.common.config;

import java.io.Serializable;
import java.time.Instant;

/**
 * 通用 API 响应结果封装
 *
 * @param <T> 响应数据类型
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 是否成功 */
    private boolean success;
    /** 业务状态码 */
    private int code;
    /** 提示或错误消息 */
    private String message;
    /** 具体数据 */
    private T data;
    /** 响应时间戳（UTC 毫秒） */
    private long timestamp;

    // ========= Constructors =========

    public ApiResult() {
        this.timestamp = Instant.now().toEpochMilli();
    }

    private ApiResult(boolean success, int code, String message, T data) {
        this.success   = success;
        this.code      = code;
        this.message   = message;
        this.data      = data;
        this.timestamp = Instant.now().toEpochMilli();
    }

    // ========= Static Factory Methods =========

    /** 返回一个操作成功但无数据的结果 */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(true, 200, "OK", null);
    }

    /** 返回一个操作成功且带数据的结果 */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, 200, "OK", data);
    }

    /** 自定义操作成功的状态码、消息和数据 */
    public static <T> ApiResult<T> success(int code, String message, T data) {
        return new ApiResult<>(true, code, message, data);
    }

    /** 返回一个操作失败的结果 */
    public static <T> ApiResult<T> failure(int code, String message) {
        return new ApiResult<>(false, code, message, null);
    }

    /** 通用错误（500） */
    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<>(false, 500, message, null);
    }

    // ========= Builder-Style Setters =========

    public ApiResult<T> withCode(int code) {
        this.code = code;
        return this;
    }

    public ApiResult<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResult<T> withData(T data) {
        this.data = data;
        return this;
    }

    public ApiResult<T> markSuccess() {
        this.success = true;
        return this;
    }

    public ApiResult<T> markFailure() {
        this.success = false;
        return this;
    }

    // ========= Getters & Setters =========

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
