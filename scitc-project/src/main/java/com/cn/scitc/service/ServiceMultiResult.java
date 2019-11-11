package com.cn.scitc.service;

import java.util.List;

/**
 * 服务通用结果集
 */
public class ServiceMultiResult<T>{
    private Long tatal;
    private List<T> result;

    public ServiceMultiResult(Long tatal, List<T> result) {
        this.tatal = tatal;
        this.result = result;
    }

    public Long getTatal() {
        return tatal;
    }

    public void setTatal(Long tatal) {
        this.tatal = tatal;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


    public int getResultSize() {
        if (this.result == null) {
            return 0;
        }
        return this.result.size();
    }
}
