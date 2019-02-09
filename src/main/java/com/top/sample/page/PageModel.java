package com.top.sample.page;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页实体类
 *
 * @author wangzhikang
 */
public class PageModel<T> {

    private int pageNo;

    private int pageSize;

    private List<T> content;

    private long total;

    public static <T> PageModel<T> from(Page<T> page) {
        PageModel<T> pageModel = new PageModel<>();

        pageModel.setTotal(page.getTotalElements());
        pageModel.setPageNo(page.getNumber() + 1);
        pageModel.setPageSize(page.getSize());
        pageModel.setContent(page.getContent());
        return pageModel;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
