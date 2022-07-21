package com.ruoyi.bookstore.test.service.impl;

import java.util.List;

import com.ruoyi.bookstore.test.domain.BookTest;
import com.ruoyi.bookstore.test.mapper.BookTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bookstore.test.service.IBookTestService;

/**
 * 测试Service业务层处理
 * 
 * @author zj
 * @date 2022-07-13
 */
@Service
public class BookTestServiceImpl implements IBookTestService 
{
    @Autowired
    private BookTestMapper bookTestMapper;

    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    @Override
    public BookTest selectBookTestById(Long id)
    {
        return bookTestMapper.selectBookTestById(id);
    }

    /**
     * 查询测试列表
     * 
     * @param bookTest 测试
     * @return 测试
     */
    @Override
    public List<BookTest> selectBookTestList(BookTest bookTest)
    {
        return bookTestMapper.selectBookTestList(bookTest);
    }

    /**
     * 新增测试
     * 
     * @param bookTest 测试
     * @return 结果
     */
    @Override
    public int insertBookTest(BookTest bookTest)
    {
        return bookTestMapper.insertBookTest(bookTest);
    }

    /**
     * 修改测试
     * 
     * @param bookTest 测试
     * @return 结果
     */
    @Override
    public int updateBookTest(BookTest bookTest)
    {
        return bookTestMapper.updateBookTest(bookTest);
    }

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键
     * @return 结果
     */
    @Override
    public int deleteBookTestByIds(Long[] ids)
    {
        return bookTestMapper.deleteBookTestByIds(ids);
    }

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    @Override
    public int deleteBookTestById(Long id)
    {
        return bookTestMapper.deleteBookTestById(id);
    }
}
