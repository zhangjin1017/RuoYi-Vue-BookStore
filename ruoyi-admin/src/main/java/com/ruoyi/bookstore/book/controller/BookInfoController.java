package com.ruoyi.bookstore.book.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bookstore.book.domain.BookInfo;
import com.ruoyi.bookstore.book.service.IBookInfoService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书信息Controller
 * 
 * @author zj
 * @date 2022-07-12
 */
@RestController
@RequestMapping("/book/book")
public class BookInfoController extends BaseController
{
    @Autowired
    private IBookInfoService bookInfoService;

    /**
     * 查询图书信息列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookInfo bookInfo)
    {
        startPage();
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        return getDataTable(list);
    }

    /**
     * 导出图书信息列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:export')")
    @Log(title = "图书信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookInfo bookInfo)
    {
        List<BookInfo> list = bookInfoService.selectBookInfoList(bookInfo);
        ExcelUtil<BookInfo> util = new ExcelUtil<BookInfo>(BookInfo.class);
        util.exportExcel(response, list, "图书信息数据");
    }

    /**
     * 获取图书信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bookInfoService.selectBookInfoById(id));
    }

    /**
     * 新增图书信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:add')")
    @Log(title = "图书信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookInfo bookInfo)
    {
        return toAjax(bookInfoService.insertBookInfo(bookInfo));
    }

    /**
     * 修改图书信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:edit')")
    @Log(title = "图书信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookInfo bookInfo)
    {
        return toAjax(bookInfoService.updateBookInfo(bookInfo));
    }

    /**
     * 删除图书信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:book:remove')")
    @Log(title = "图书信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookInfoService.deleteBookInfoByIds(ids));
    }
}
