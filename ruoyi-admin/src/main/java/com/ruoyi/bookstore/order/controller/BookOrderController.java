package com.ruoyi.bookstore.order.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ruoyi.bookstore.book.domain.BookInfo;
import com.ruoyi.bookstore.book.service.IBookInfoService;
import com.ruoyi.bookstore.cart.domain.BookCart;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bookstore.order.domain.BookOrder;
import com.ruoyi.bookstore.order.service.IBookOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author 张锦
 * @date 2022-07-16
 */
@RestController
@RequestMapping("/order/order")
public class BookOrderController extends BaseController
{
    @Autowired
    private IBookOrderService bookOrderService;

    @Autowired
    private IBookInfoService bookInfoService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookOrder bookOrder)
    {
        startPage();
        List<BookOrder> list = bookOrderService.selectBookOrderList(bookOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookOrder bookOrder)
    {
        List<BookOrder> list = bookOrderService.selectBookOrderList(bookOrder);
        ExcelUtil<BookOrder> util = new ExcelUtil<BookOrder>(BookOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bookOrderService.selectBookOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookOrder bookOrder)
    {
        return toAjax(bookOrderService.insertBookOrder(bookOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookOrder bookOrder)
    {
        return toAjax(bookOrderService.updateBookOrder(bookOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('bookstore:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookOrderService.deleteBookOrderByIds(ids));
    }


    /**
     * 查询我的订单列表
     */
    @GetMapping("/getMyOrder")
    public TableDataInfo getMyOrder()
    {
        //从redis中获取用户id
        Long userId = SecurityUtils.getUserId();
        System.out.println("userId:"+userId);

        startPage();
        List<BookOrder> list = bookOrderService.list(new LambdaQueryWrapper<BookOrder>().eq(BookOrder::getUserId,userId));

        for (int i = 0; i < list.size(); i++) {

            String bookIds = list.get(i).getBookId();
            String[] bookId = bookIds.split(",");

            String everyBookCount=list.get(i).getEveryBookCount();
            String[] everyCount = everyBookCount.split(",");

            List<BookInfo> bookInfoList = new ArrayList<>();
            for (int j = 0; j < bookId.length; j++) {
                BookInfo bookInfo = bookInfoService.getById(Long.parseLong(bookId[j]));
                bookInfo.setTotal(Long.valueOf(everyCount[j]));
                bookInfoList.add(bookInfo);
            }
            list.get(i).setBookInfoList(bookInfoList);
        }
        return getDataTable(list);
    }


}
