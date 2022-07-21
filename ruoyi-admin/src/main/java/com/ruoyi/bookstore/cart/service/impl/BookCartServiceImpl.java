package com.ruoyi.bookstore.cart.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bookstore.cart.mapper.BookCartMapper;
import com.ruoyi.bookstore.cart.domain.BookCart;
import com.ruoyi.bookstore.cart.service.IBookCartService;

/**
 * 购物车信息管理Service业务层处理
 * 
 * @author 张锦
 * @date 2022-07-16
 */
@Service
public class BookCartServiceImpl extends ServiceImpl<BookCartMapper,BookCart> implements IBookCartService
{
    @Autowired
    private BookCartMapper bookCartMapper;

    /**
     * 查询购物车信息管理
     * 
     * @param id 购物车信息管理主键
     * @return 购物车信息管理
     */
    @Override
    public BookCart selectBookCartById(Long id)
    {
        return bookCartMapper.selectBookCartById(id);
    }

    /**
     * 查询购物车信息管理列表
     * 
     * @param bookCart 购物车信息管理
     * @return 购物车信息管理
     */
    @Override
    public List<BookCart> selectBookCartList(BookCart bookCart)
    {
        return bookCartMapper.selectBookCartList(bookCart);
    }

    /**
     * 新增购物车信息管理
     * 
     * @param bookCart 购物车信息管理
     * @return 结果
     */
    @Override
    public int insertBookCart(BookCart bookCart)
    {
        return bookCartMapper.insertBookCart(bookCart);
    }

    /**
     * 修改购物车信息管理
     * 
     * @param bookCart 购物车信息管理
     * @return 结果
     */
    @Override
    public int updateBookCart(BookCart bookCart)
    {
        return bookCartMapper.updateBookCart(bookCart);
    }

    /**
     * 批量删除购物车信息管理
     * 
     * @param ids 需要删除的购物车信息管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCartByIds(Long[] ids)
    {
        return bookCartMapper.deleteBookCartByIds(ids);
    }

    /**
     * 删除购物车信息管理信息
     * 
     * @param id 购物车信息管理主键
     * @return 结果
     */
    @Override
    public int deleteBookCartById(Long id)
    {
        return bookCartMapper.deleteBookCartById(id);
    }
}
