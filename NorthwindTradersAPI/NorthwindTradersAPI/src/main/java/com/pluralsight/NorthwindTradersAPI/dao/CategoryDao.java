package com.pluralsight.NorthwindTradersAPI.dao;

import java.util.List;

public interface CategoryDao {


    List<CategoryDao> getAll();//either category
    CategoryDao searchById(int id);

}
