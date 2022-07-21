package com.ruoyi.bookstore.test.mapper;

import java.util.List;
import com.ruoyi.bookstore.test.domain.BookTest;

/**
 * 测试Mapper接口
 * 
 * @author zj
 * @date 2022-07-13
 */
public interface BookTestMapper 
{
    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    public BookTest selectBookTestById(Long id);

    /**
     * 查询测试列表
     * 
     * @param bookTest 测试
     * @return 测试集合
     */
    public List<BookTest> selectBookTestList(BookTest bookTest);

    /**
     * 新增测试
     * 
     * @param bookTest 测试
     * @return 结果
     */
    public int insertBookTest(BookTest bookTest);

    /**
     * 修改测试
     * 
     * @param bookTest 测试
     * @return 结果
     */
    public int updateBookTest(BookTest bookTest);

    /**
     * 删除测试
     * 
     * @param id 测试主键
     * @return 结果
     */
    public int deleteBookTestById(Long id);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookTestByIds(Long[] ids);
}
