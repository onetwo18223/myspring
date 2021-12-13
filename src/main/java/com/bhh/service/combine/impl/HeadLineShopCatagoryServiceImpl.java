package com.bhh.service.combine.impl;

import com.bhh.entity.bo.HeadLine;
import com.bhh.entity.bo.HeadLineShopCatagory;
import com.bhh.entity.bo.ShopCatagory;
import com.bhh.entity.dto.Result;
import com.bhh.service.combine.HeadLineShopCatagoryService;
import com.bhh.service.solo.HeadLineService;
import com.bhh.service.solo.Impl.HeadLineServiceImpl;
import com.bhh.service.solo.Impl.ShopCatagoryServiceImpl;
import com.bhh.service.solo.ShopCatagoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCatagoryServiceImpl implements HeadLineShopCatagoryService {
    @Autowired(value = "com.bhh.service.solo.Impl.AHeadLineServiceImpl")
    private HeadLineService headLineService;
    @Autowired
    private ShopCatagoryService shopCatagoryService;

    @Override
    public Result<HeadLineShopCatagory> getMainPageInfo() {

        HeadLine headLine = new HeadLine();
        Result<Boolean> judge = headLineService.addHeadLine(headLine);
        System.out.println(judge.getData());

        ShopCatagory shopCatagory = new ShopCatagory();
        Result<List<ShopCatagory>> shopCatagoryList = shopCatagoryService.queryShopCatagoryList(shopCatagory, 1, 100);

        HeadLineShopCatagory headLineShopCatagory = new HeadLineShopCatagory();
        headLineShopCatagory.setShopCatagorieList(shopCatagoryList.getData());

        Result<HeadLineShopCatagory> result = new Result<>();
        result.setData(headLineShopCatagory);

        return result;
    }
}
