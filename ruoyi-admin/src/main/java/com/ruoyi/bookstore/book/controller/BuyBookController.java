package com.ruoyi.bookstore.book.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.bookstore.book.domain.BookInfo;
import com.ruoyi.bookstore.book.service.IBookInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/buyBook")
public class BuyBookController extends BaseController
{
    @Autowired
    private IBookInfoService bookInfoService;

    /**
     * 查询图书信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookInfo bookInfo)
    {
        startPage();
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        return getDataTable(list);
    }
    /**
     * 查询图书分类列表
     */
    @GetMapping("/getAllClass")
    public Set<String> getAllClass()
    {
        List<BookInfo> list = bookInfoService.selectBookInfoList(new BookInfo());
        Set<String> classSet = new LinkedHashSet<String>();
        list.forEach(bookInfo ->{
            classSet.add(bookInfo.getBookClass());
        });
        return classSet;
    }
    /**
     * 查询图书分类列表
     */
    @GetMapping("/getClassBooks")
    public TableDataInfo getClassBooks()
    {
        PageHelper.startPage(1,8);
        List<BookInfo> list = bookInfoService.selectBookInfoList(new BookInfo());
        return getDataTable(list);
    }
}
