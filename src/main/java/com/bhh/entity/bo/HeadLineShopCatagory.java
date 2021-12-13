package com.bhh.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class HeadLineShopCatagory {
    private List<HeadLine> headLineList;
    private List<ShopCatagory> shopCatagorieList;

}
