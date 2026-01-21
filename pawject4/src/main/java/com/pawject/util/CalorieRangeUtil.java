package com.pawject.util;

import com.pawject.dto.food.SearchPetfoodCondition;

public class CalorieRangeUtil {

    public static void calorie(SearchPetfoodCondition con) {
        if (con.getMinvalue() != null && con.getMinvalue() < 0)
            con.setMinvalue(null);
        if (con.getMaxvalue() != null && con.getMaxvalue() < 0)
            con.setMaxvalue(null);
    }
}