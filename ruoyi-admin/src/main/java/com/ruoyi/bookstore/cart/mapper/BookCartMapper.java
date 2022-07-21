package com.ruoyi.bookstore.cart.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.bookstore.cart.domain.BookCart;

/**
 * 购物车信息管理Mapper接口
 * 
 * @author 张锦
 * @date 2022-07-16
 */
public interface BookCartMapper extends BaseMapper<BookCart>
{
    /**
     * 查询购物车信息管理
     * 
     * @param id 购物车信息管理主键
     * @return 购物车信息管理
     */
    public BookCart selectBookCartById(Long id);

    /**
     * 查询购物车信息管理列表
     * 
     * @param bookCart 购物车信息管理
     * @return 购物车信息管理集合
     */
    public List<BookCart> selectBookCartList(BookCart bookCart);

    /**
     * 新增购物车信息管理
     * 
     * @param bookCart 购物车信息管理
     * @return 结果
     */
    public int insertBookCart(BookCart bookCart);

    /**
     * 修改购物车信息管理
     * 
     * @param bookCart 购物车信息管理
     * @return 结果
     */
    public int updateBookCart(BookCart bookCart);

    /**
     * 删除购物车信息管理
     * 
     * @param id 购物车信息管理主键
     * @return 结果
     */
    public int deleteBookCartById(Long id);

    /**
     * 批量删除购物车信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookCartByIds(Long[] ids);
}
