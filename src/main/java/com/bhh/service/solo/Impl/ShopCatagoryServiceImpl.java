package com.bhh.service.solo.Impl;

import com.bhh.entity.bo.ShopCatagory;
import com.bhh.entity.dto.Result;
import com.bhh.service.solo.ShopCatagoryService;
import org.simpleframework.core.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCatagoryServiceImpl implements ShopCatagoryService {
    @Override
    public Result<Boolean> addShopCatagory(ShopCatagory shopCatagory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCatagory(Integer shopCatagoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCatagory(ShopCatagory shopCatagory) {
        return null;
    }

    @Override
    public Result<ShopCatagory> queryShopCatagoryById(Integer shopCatagoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCatagory>> queryShopCatagoryList(ShopCatagory shopCatagory, Integer pageNumber, Integer pageSize) {

        Result<List<ShopCatagory>> result = new Result<>();
        List<ShopCatagory> shopCatagoryList= new ArrayList<>();
        shopCatagoryList.add(new ShopCatagory());
        result.setData(shopCatagoryList);
        System.out.println("ShopCatagoryServiceImpl.queryShopCatagoryList");

        return result;
    }
}
