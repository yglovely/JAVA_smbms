package com.yg.util;

public class PageUtil {
    private int index; //当前页数
    private int size = 5; //当前获取数据的数量 默认为5
    private int total;// 当前数据总数量
    private int totalpage; //总页数

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if (index > 0) {
            this.index = index;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        if (total > 0) {
            this.total = total;
        }
    }

    public int getTotalpage() {
        //总数量除以获取的数量就是页数
        if (total % size > 0) {
            totalpage = (total / size) + 1;
        } else if (total % size == 0) {
            totalpage = (this.total / size);
        }
        return totalpage;
    }


}