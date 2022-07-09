package com.qinyue.vcommon.http;


import java.util.ArrayList;
import java.util.List;

/**
 * @类名 PageList
 * @作者 bodia
 * @时间 7/9/22 2:37 PM
 * @描述 分页数据解析基类
 */
public class PageList<T> {
    /**
     * 数据集
     */
    private List<T> list;

    private HttpPageInfoBean pageInfo;

    public HttpPageInfoBean getPageInfo() {
        if (pageInfo==null){
            return new HttpPageInfoBean();
        }
        return pageInfo;
    }

    public void setPageInfo(HttpPageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        if (list==null){
            return new ArrayList<>();
        }
        return list;
    }

    public void setData(List<T> data) {
        this.list = data;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "list=" + list +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
