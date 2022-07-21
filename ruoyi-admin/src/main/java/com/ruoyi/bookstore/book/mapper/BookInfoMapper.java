package com.ruoyi.bookstore.book.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.bookstore.book.domain.BookInfo;

/**
 * 图书信息Mapper接口
 * 
 * @author zj
 * @date 2022-07-12
 */
public interface BookInfoMapper extends BaseMapper<BookInfo>
{
//    根据id和数量减少库存
    public void reduceCount(Long bookId,Long count);
    /**
     * 查询图书信息
     * 
     * @param id 图书信息主键
     * @return 图书信息
     */
    public BookInfo selectBookInfoById(Long id);

    /**
     * 查询图书信息列表
     * 
     * @param bookInfo 图书信息
     * @return 图书信息集合
     */
    public List<BookInfo> selectBookInfoList(BookInfo bookInfo);

    /**
     * 新增图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int insertBookInfo(BookInfo bookInfo);

    /**
     * 修改图书信息
     * 
     * @param bookInfo 图书信息
     * @return 结果
     */
    public int updateBookInfo(BookInfo bookInfo);

    /**
     * 删除图书信息
     * 
     * @param id 图书信息主键
     * @return 结果
     */
    public int deleteBookInfoById(Long id);

    /**
     * 批量删除图书信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookInfoByIds(Long[] ids);
}
