package com.bhh.service.solo.Impl;

import com.bhh.entity.bo.HeadLine;
import com.bhh.entity.dto.Result;
import com.bhh.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        Result<Boolean> result = new Result<>();
        result.setData(new Boolean(true));
        System.out.println("addHeadLine");
        return result;
    }

    @Override
    public Result<Boolean> removeHeadLine(Integer headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(Integer headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLineList(HeadLine headLine, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
