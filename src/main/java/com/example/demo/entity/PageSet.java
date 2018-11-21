package com.example.demo.entity;

public class PageSet {
    // 前端传入的页码
    private int pageNo;  // 从1开始

    // 每页的条数
    private int pageSize;

    // 数据库的偏移
    private int offSet;

    // 数据库的大小限制
    private int limit;

    public int getOffSet() {
        return (getPageNo() -1)* getPageSize();
    }


    public int getLimit() {
        return getPageSize();
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
}
