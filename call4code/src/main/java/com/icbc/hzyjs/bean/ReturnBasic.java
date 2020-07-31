package com.icbc.hzyjs.bean;

/**
 * @author kfzx-zhoux.
 * @date 2020-07
 * @description
 */
public class ReturnBasic<T> {
    private boolean success;
    private T data;
    private String message;

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static class Builder<T> {
        private boolean success;
        private T data;
        private String message;

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public <T> ReturnBasic<T> build() {
            ReturnBasic<T> rb = new ReturnBasic<T>();
            rb.setMessage(this.message);
            rb.setSuccess(this.success);
            rb.setData((T) this.data);
            return rb;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
