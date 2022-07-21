package com.ruoyi.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.bookstore.book.domain.BookInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车信息管理对象 book_cart
 * 
 * @author 张锦
 * @date 2022-07-16
 */
public class BookCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 图书Id */
    @Excel(name = "图书Id")
    private Long bookId;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long userId;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal price;

    /** 加购时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "加购时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @TableField(exist = false)
    private BookInfo bookInfo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
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
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("userId", getUserId())
            .append("count", getCount())
            .append("price", getPrice())
            .append("addTime", getAddTime())
            .toString();
    }
}
