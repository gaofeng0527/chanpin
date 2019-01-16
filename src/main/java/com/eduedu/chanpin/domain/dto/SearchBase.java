package com.eduedu.chanpin.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/22.
 */

@Setter
@Getter
public class SearchBase {
    private int pageSize;
    private int currentPage;
    private int offset;
    private int pageCount;
    private String status;

    private void calculateOffset() {
        if (1 > getCurrentPage()) {
            setCurrentPage(1);
        }
        setOffset(getPageSize() * (getCurrentPage() - 1));
    }

    private void calculatePageCount(int totalCount) {
        int remainder = totalCount % getPageSize();

        if (0 == remainder) {
            setPageCount(totalCount / getPageSize());
        } else {
            setPageCount((totalCount / getPageSize()) + 1);
        }
    }

    public void setUp(int totalCount) {
        calculatePageCount(totalCount);
        calculateOffset();
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<String>();

        if (getCurrentPage() == 0) {
            errors.add("参数 currentPage 缺失或为 0");
        }

        if (getPageSize() == 0) {
            errors.add("参数 pageSize 缺失或为 0");
        }

        return errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
