package com.bhh.service.solo.Impl;

import com.bhh.entity.bo.HeadLine;
import com.bhh.entity.dto.Result;
import com.bhh.service.solo.HeadLineService;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Service;

import java.util.List;
@Slf4j
@Service
public class AHeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
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
