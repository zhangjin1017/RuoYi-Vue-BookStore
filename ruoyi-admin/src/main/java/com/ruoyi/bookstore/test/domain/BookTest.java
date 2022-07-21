package com.ruoyi.bookstore.test.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试对象 book_test
 *
 * @author zj
 * @date 2022-07-13
 */
public class BookTest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 状态 */
    @Excel(name = "状态")
    private String bookStatus;

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
    public void setBookStatus(String bookStatus)
    {
        this.bookStatus = bookStatus;
    }

    public String getBookStatus()
    {
        return bookStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("bookName", getBookName())
                .append("bookStatus", getBookStatus())
                .toString();
    }
}
