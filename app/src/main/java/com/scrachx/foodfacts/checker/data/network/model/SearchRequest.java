package com.scrachx.foodfacts.checker.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by scots on 06/05/2017.
 */
public class SearchRequest {

    @Expose
    @SerializedName("search_terms")
    private String search_terms;

    @Expose
    @SerializedName("page")
    private int page;

    public SearchRequest(String search_terms, int page) {
        this.search_terms = search_terms;
        this.page = page;
    }

    public String getSearch_terms() {
        return search_terms;
    }

    public void setSearch_terms(String search_terms) {
        this.search_terms = search_terms;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        if (page != that.page) return false;
        return search_terms.equals(that.search_terms);

    }

    @Override
    public int hashCode() {
        int result = search_terms.hashCode();
        result = 31 * result + page;
        return result;
    }

}
