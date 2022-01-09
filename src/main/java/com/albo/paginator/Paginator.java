package com.albo.paginator;

import java.util.ArrayList;
import java.util.List;

public class Paginator {

    public int getCountOfPages(int limit, int countOfElements) {
        double countOfPages = Math.ceil((double) countOfElements / limit);
        return (int) countOfPages;
    }


    public int getOffset(int currentPage, int limit) {
        return (currentPage - 1) * limit;
    }

    public List<Integer> getListOfPages(int currentPage, int countOfPages) {
        int maxCountOfVisiblePages = 10;
        List<Integer> listOfPages = new ArrayList<>();
        if (countOfPages < maxCountOfVisiblePages + 1) {
            for (int i = 1; i < countOfPages + 1; i++) {
                listOfPages.add(i);
            }
        } else {
            if (currentPage < 7) {
                for (int i = 1; i < maxCountOfVisiblePages + 1; i++) {
                    listOfPages.add(i);
                }
            } else {
                if (currentPage + 4 > countOfPages) {
                    int startPage = countOfPages - (maxCountOfVisiblePages - 1);
                    for (int i = 0; i < maxCountOfVisiblePages; i++) {
                        listOfPages.add(startPage + i);
                    }
                } else {
                    int startPage = currentPage - 5;
                    for (int i = 0; i < maxCountOfVisiblePages; i++) {
                        listOfPages.add(startPage + i);
                    }
                }
            }
        }
        return listOfPages;
    }
}
