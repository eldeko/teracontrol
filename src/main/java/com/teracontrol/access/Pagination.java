package com.teracontrol.access;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Pagination {
    
   @Getter @Setter private int activePage;
   @Getter @Setter private int itemsCountPerPage;
    
    public Pagination() {
    }

    public Pagination(int activePage, int itemsCountPerPage) {
        this.activePage = activePage;
        this.itemsCountPerPage = itemsCountPerPage;
    }
}