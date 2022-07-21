package com.ruoyi.bookstore.book.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.bookstore.book.domain.BookInfo;
import com.ruoyi.bookstore.book.mapper.BookInfoMapper;
import com.ruoyi.bookstore.book.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图书信息Service业务层处理
 * 
 * @author zj
 * @date 2022-07-12
 */
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements IBookInfoService
{
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public void reduceCount(Long bookId, Long count) {
        bookInfoMapper.reduceCount(bookId,count);
    }

    /**
     * 查询图书信息
     *
     * @param id 图书信息主键
     * @return 图书信息
     */
    @Override
    public BookInfo selectBookInfoById(Long id)
    {
        return bookInfoMapper.selectBookInfoById(id);
    }

    /**
     * 查询图书信息列表
     *
     * @param bookInfo 图书信息
     * @return 图书信息
     */
    @Override
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo)
    {
        return bookInfoMapper.selectBookInfoList(bookInfo);
    }

    /**
     * 新增图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int insertBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.insertBookInfo(bookInfo);
    }

    /**
     * 修改图书信息
     *
     * @param bookInfo 图书信息
     * @return 结果
     */
    @Override
    public int updateBookInfo(BookInfo bookInfo)
    {
        return bookInfoMapper.updateBookInfo(bookInfo);
    }

    /**
     * 批量删除图书信息
     *
     * @param ids 需要删除的图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoByIds(Long[] ids)
    {
        return bookInfoMapper.deleteBookInfoByIds(ids);
    }

    /**
     * 删除图书信息信息
     *
     * @param id 图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBookInfoById(Long id)
    {
        return bookInfoMapper.deleteBookInfoById(id);
    }
}
