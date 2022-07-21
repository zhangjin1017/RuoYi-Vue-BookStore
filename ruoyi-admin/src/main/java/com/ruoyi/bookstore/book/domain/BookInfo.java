package com.ruoyi.bookstore.book.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书信息对象 book_info
 * 
 * @author zj
 * @date 2022-07-12
 */
public class BookInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 封面 */
    @Excel(name = "封面")
    private String picture;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 简介 */
    @Excel(name = "简介")
    private String content;

    /** 出版社 */
    @Excel(name = "出版社")
    private String publish;

    /** 分类 */
    @Excel(name = "分类")
    private String bookClass;

    /** total */
    @Excel(name = "total")
    private Long total;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPublish(String publish) 
    {
        this.publish = publish;
    }

    public String getPublish() 
    {
        return publish;
    }
    public void setBookClass(String bookClass) 
    {
        this.bookClass = bookClass;
    }

    public String getBookClass() 
    {
        return bookClass;
    }
    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookName", getBookName())
            .append("author", getAuthor())
            .append("picture", getPicture())
            .append("price", getPrice())
            .append("content", getContent())
            .append("publish", getPublish())
            .append("bookClass", getBookClass())
            .append("total", getTotal())
            .toString();
    }
}
