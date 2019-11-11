package com.cn.scitc.echarts;

import java.util.List;

/**
 * 填充数据格式
 * @author jswzj
 */
public class echarts {

    private Long value;
    private String  name;



    public echarts() {
    }

    public echarts(Long value, String name) {
        this.value = value;
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "echarts{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
