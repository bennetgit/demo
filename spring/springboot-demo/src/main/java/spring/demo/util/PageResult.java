package spring.demo.util;

import java.util.List;

public class PageResult<T> {

    private Long total;

    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public static final <T> PageResult of(Long total, List<T> rows) {
        return new PageResult(total, rows);
    }
}
