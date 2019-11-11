package com.cn.scitc.web.form;

public class RentSearch {

    private String keywords;//搜索关键字
    private String orderBy = "lastUpdateTime";//分组最后更新时间
    private String orderDirection = "desc";//



    private int start = 0;

    private int size = 5;

    public int getStart() {
        return start > 0 ? start : 0;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        if (this.size < 1) {
            return 5;
        } else if (this.size > 100) {
            return 100;
        } else {
            return this.size;
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    @Override
    public String toString() {
        return "RentSearch{" +
                "keywords='" + keywords + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", orderDirection='" + orderDirection + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
