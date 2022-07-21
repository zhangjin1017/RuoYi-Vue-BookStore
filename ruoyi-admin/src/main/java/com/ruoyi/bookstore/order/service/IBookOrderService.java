package com.ruoyi.bookstore.order.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.bookstore.order.domain.BookOrder;

/**
 * 订单Service接口
 * 
 * @author 张锦
 * @date 2022-07-16
 */
public interface IBookOrderService extends IService<BookOrder>
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public BookOrder selectBookOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param bookOrder 订单
     * @return 订单集合
     */
    public List<BookOrder> selectBookOrderList(BookOrder bookOrder);

    /**
     * 新增订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    public int insertBookOrder(BookOrder bookOrder);

    /**
     * 修改订单
     * 
     * @param bookOrder 订单
     * @return 结果
     */
    public int updateBookOrder(BookOrder bookOrder);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteBookOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBookOrderById(Long id);
}
