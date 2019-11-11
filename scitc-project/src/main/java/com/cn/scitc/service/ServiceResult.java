package com.cn.scitc.service;

/**
 * 服务接口通用结果集
 */
public class ServiceResult<T> {
    private boolean  success;//表示成功状态
    private String message;//状态信息
    private T resutl;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T resutl) {
        this.success = success;
        this.message = message;
        this.resutl = resutl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResutl() {
        return resutl;
    }

    public void setResutl(T resutl) {
        this.resutl = resutl;
    }

    //保存结果集
    public static <T> ServiceResult<T> success(){
        return new ServiceResult<>(true);
    }

    public  static <T> ServiceResult<T> of(T result){
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResutl(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> noutFound(){
        return new ServiceResult<>(false,Message.NOT_FOUND.getValue());
    }

    /**
     * 自定义枚举信息
     */

    public  enum Message{
        NOT_FOUND("Not Found Resource!"),
        NOT_LOGIN("User not login!");
        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
