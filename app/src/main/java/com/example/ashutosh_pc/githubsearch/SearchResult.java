package com.example.ashutosh_pc.githubsearch;

import java.util.ArrayList;

public class SearchResult {
    Integer total_count;
    Boolean incomplete_results;
    ArrayList<Items> items;

    public SearchResult() {
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public SearchResult(Integer total_count, Boolean incomplete_results, ArrayList<Items> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }
}
