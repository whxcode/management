package com.cn.scitc.web.form;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 公告信息表单验证
 */
public class MessageForm {
    @ApiModelProperty("编号")
    private Long id;
    @NotNull(message = "标题信息不能为空")
    @Size(min = 4,max = 30,message = "标题信息只能是4到30个字符")
    @ApiModelProperty("标题")
    private String title;
    @NotNull(message = "内容信息不能为空")
    @Size(min = 10,max = 255,message = "内容信息长度最小长度至少是10")
    @ApiModelProperty("正文")
    private String context;
    @NotNull(message = "作者不能为空")
    @Size(min = 2,message = "作者长度最少2个字符")
    @ApiModelProperty("作者")
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date lastUpdateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "MessageForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
