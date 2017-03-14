package spring.demo.dto.request;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import spring.demo.util.JsonDateTimeDeserializer;

public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -3226404196843843131L;

    private int pageNumber;

    private int pageSize;

    private String sortName;

    private String sortOrder;

    private String searchText;

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private LocalDateTime requestTimeStart;

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private LocalDateTime requestTimeEnd;

    public LocalDateTime getRequestTimeStart() {
        return requestTimeStart;
    }

    public void setRequestTimeStart(LocalDateTime requestTimeStart) {
        this.requestTimeStart = requestTimeStart;
    }

    public LocalDateTime getRequestTimeEnd() {
        return requestTimeEnd;
    }

    public void setRequestTimeEnd(LocalDateTime requestTimeEnd) {
        this.requestTimeEnd = requestTimeEnd;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
