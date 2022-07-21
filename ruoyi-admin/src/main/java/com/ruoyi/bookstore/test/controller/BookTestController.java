package com.ruoyi.bookstore.test.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bookstore.test.domain.BookTest;
import com.ruoyi.bookstore.test.service.IBookTestService;
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
 * 测试Controller
 * 
 * @author zj
 * @date 2022-07-13
 */
@RestController
@RequestMapping("/test/test")
public class BookTestController extends BaseController
{
    @Autowired
    private IBookTestService bookTestService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookTest bookTest)
    {
        startPage();
        List<BookTest> list = bookTestService.selectBookTestList(bookTest);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookTest bookTest)
    {
        List<BookTest> list = bookTestService.selectBookTestList(bookTest);
        ExcelUtil<BookTest> util = new ExcelUtil<BookTest>(BookTest.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bookTestService.selectBookTestById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookTest bookTest)
    {
        return toAjax(bookTestService.insertBookTest(bookTest));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookTest bookTest)
    {
        return toAjax(bookTestService.updateBookTest(bookTest));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('bookstore:test:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookTestService.deleteBookTestByIds(ids));
    }
}
