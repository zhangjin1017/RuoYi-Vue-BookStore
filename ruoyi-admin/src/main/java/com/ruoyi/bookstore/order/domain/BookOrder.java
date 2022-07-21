package com.ruoyi.bookstore.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.bookstore.book.domain.BookInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 book_order
 * 
 * @author 张锦
 * @date 2022-07-16
 */
public class BookOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 图书Id */
    @Excel(name = "图书Id")
    private String bookId;

    /** 每种图书订单数量 */
    @Excel(name = "每种图书订单数量")
    private String everyBookCount;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long userId;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal price;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    @TableField(exist = false)
    private List<BookInfo> bookInfoList;

    public List<BookInfo> getBookInfoList() {
        return bookInfoList;
    }

    public void setBookInfoList(List<BookInfo> bookInfoList) {
        this.bookInfoList = bookInfoList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }

    public String getEveryBookCount() {
        return everyBookCount;
    }

    public void setEveryBookCount(String everyBookCount) {
        this.everyBookCount = everyBookCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("userId", getUserId())
            .append("count", getCount())
            .append("price", getPrice())
            .append("payTime", getPayTime())
            .toString();
    }
}
