package com.bhh.service.solo;


import com.bhh.entity.bo.ShopCatagory;
import com.bhh.entity.dto.Result;

import java.util.List;

public interface ShopCatagoryService {
    Result<Boolean> addShopCatagory(ShopCatagory shopCatagory);
    Result<Boolean> removeShopCatagory(Integer shopCatagoryId);
    Result<Boolean> modifyShopCatagory(ShopCatagory shopCatagory);
    Result<ShopCatagory> queryShopCatagoryById(Integer shopCatagoryId);
    Result<List<ShopCatagory>> queryShopCatagoryList(ShopCatagory shopCatagory, Integer pageNumber , Integer pageSize);
}
