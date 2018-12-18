package com.nuaa.project.model;

/**
 * @Auther: cyw35
 * @Date: 2018/12/18 18:56
 * @Description:
 */
public class PageQo {
    private Integer page = 0;
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
