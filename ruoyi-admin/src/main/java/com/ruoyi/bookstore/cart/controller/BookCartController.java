package com.ruoyi.bookstore.cart.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.bookstore.book.domain.BookInfo;
import com.ruoyi.bookstore.book.service.IBookInfoService;
import com.ruoyi.bookstore.order.domain.BookOrder;
import com.ruoyi.bookstore.order.service.IBookOrderService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bookstore.cart.domain.BookCart;
import com.ruoyi.bookstore.cart.service.IBookCartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车信息管理Controller
 * 
 * @author 张锦
 * @date 2022-07-16
 */
@RestController
@RequestMapping("/cart/cart")
public class BookCartController extends BaseController
{
    @Autowired
    private IBookCartService bookCartService;

    @Autowired
    private IBookInfoService bookInfoService;

    @Autowired
    private IBookOrderService bookOrderService;
    /**
     * 查询购物车信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookCart bookCart)
    {
        startPage();
        List<BookCart> list = bookCartService.selectBookCartList(bookCart);
        return getDataTable(list);
    }

    /**
     * 导出购物车信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:export')")
    @Log(title = "购物车信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookCart bookCart)
    {
        List<BookCart> list = bookCartService.selectBookCartList(bookCart);
        ExcelUtil<BookCart> util = new ExcelUtil<BookCart>(BookCart.class);
        util.exportExcel(response, list, "购物车信息管理数据");
    }

    /**
     * 获取购物车信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bookCartService.selectBookCartById(id));
    }

    /**
     * 新增购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:add')")
    @Log(title = "购物车信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookCart bookCart)
    {
        return toAjax(bookCartService.insertBookCart(bookCart));
    }

    /**
     * 修改购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:edit')")
    @Log(title = "购物车信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookCart bookCart)
    {
        return toAjax(bookCartService.updateBookCart(bookCart));
    }

    /**
     * 删除购物车信息管理
     */
    @PreAuthorize("@ss.hasPermi('bookstore:cart:remove')")
    @Log(title = "购物车信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookCartService.deleteBookCartByIds(ids));
    }


    /**
     * 用户点击加入购物车按钮，添加购物车信息
     */
    @PostMapping("/userAddCart")
    public AjaxResult userAddCart(@RequestBody BookCart bookCart)
    {
        //如果该商品已经在购物车
        LambdaQueryWrapper<BookCart> queryWrapper = new LambdaQueryWrapper<BookCart>();
        queryWrapper.eq(BookCart::getUserId, bookCart.getUserId());
        queryWrapper.eq(BookCart::getBookId, bookCart.getBookId());
        List<BookCart> list = bookCartService.list(queryWrapper);
        if (list.size()!=0){
            System.out.println("该商品已经在购物车");
            return AjaxResult.error("该商品已经在购物车");
        }else{
        //不在的话
        BigDecimal price = bookInfoService.selectBookInfoById(bookCart.getBookId()).getPrice();
        bookCart.setPrice(price.multiply(new BigDecimal(bookCart.getCount())));
        bookCart.setAddTime(new Date());
        System.out.println("bookCart:"+bookCart);
        return toAjax(bookCartService.insertBookCart(bookCart));
//        return toAjax(11);
        }


    }
        /**
      * 查询我的订单列表
      */
    @GetMapping("/getMyCart")
    public TableDataInfo getMyCart(BookCart bookCart)
    {
       //从redis中获取用户id
        Long userId = SecurityUtils.getUserId();
        System.out.println("userId:"+userId);

        startPage();
        List<BookCart> list = bookCartService.list(new LambdaQueryWrapper<BookCart>().eq(BookCart::getUserId,userId));

        for (int i = 0; i < list.size(); i++) {
            BookInfo bookInfo = bookInfoService.selectBookInfoById(list.get(i).getBookId());
            list.get(i).setBookInfo(bookInfo);
        }
        return getDataTable(list);
    }

    @PutMapping("/updateCartCount/{id}/{count}")
    public AjaxResult updateCartCount(@PathVariable Long id,@PathVariable Long count){
        BookCart bookCart = bookCartService.list(new LambdaQueryWrapper<BookCart>().eq(BookCart::getBookId,id)).get(0);
        bookCart.setCount(count);

        return toAjax(bookCartService.updateBookCart(bookCart));
    }

    /**
     * 用户删除购物车信息管理
     */
    @DeleteMapping("/delCartById/{id}")
    public AjaxResult delCartById(@PathVariable Long id)
    {
        return toAjax(bookCartService.deleteBookCartById(id));
    }

    /**
     * 用户结算完成 删除购物车信息 并转换为订单信息 减少库存
     */
    @DeleteMapping("/addOrderByPayed/{ids}/{count}/{price}")
    public AjaxResult addOrderByPayed(@PathVariable Long[] ids,@PathVariable Long count,@PathVariable BigDecimal price)

    {
        StringBuilder bookIds= new StringBuilder();
        StringBuilder everyBookCount= new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            BookCart bookCart = bookCartService.selectBookCartById(ids[i]);
            bookIds.append(bookCart.getBookId());
            everyBookCount.append(bookCart.getCount());
            if (i!=ids.length-1){
                bookIds.append(",");
                everyBookCount.append(",");
            }
            bookInfoService.reduceCount(bookCart.getBookId(),bookCart.getCount());
            bookCartService.deleteBookCartById(ids[i]);
        }
        BookOrder bookOrder = new BookOrder();
        bookOrder.setUserId(SecurityUtils.getUserId());
        bookOrder.setBookId(bookIds.toString());
        bookOrder.setEveryBookCount(everyBookCount.toString());
        bookOrder.setCount(count);
        bookOrder.setPrice(price);
        bookOrder.setPayTime(new Date());
        return toAjax(bookOrderService.insertBookOrder(bookOrder));
    }

}
