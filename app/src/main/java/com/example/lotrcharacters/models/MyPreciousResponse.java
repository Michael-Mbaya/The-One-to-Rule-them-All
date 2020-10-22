
package com.example.lotrcharacters.models;

import com.example.lotrcharacters.models.Doc;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class MyPreciousResponse {

    @SerializedName("docs")
    @Expose
    public List<Doc> docs = null;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("pages")
    @Expose
    public Integer pages;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyPreciousResponse() {
    }

    /**
     * 
     * @param total
     * @param pages
     * @param docs
     * @param offset
     * @param limit
     * @param page
     */
    public MyPreciousResponse(List<Doc> docs, Integer total, Integer limit, Integer offset, Integer page, Integer pages) {
        super();
        this.docs = docs;
        this.total = total;
        this.limit = limit;
        this.offset = offset;
        this.page = page;
        this.pages = pages;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
