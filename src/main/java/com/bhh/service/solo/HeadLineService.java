package com.bhh.service.solo;

import com.bhh.entity.bo.HeadLine;
import com.bhh.entity.dto.Result;

import java.util.List;

public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(Integer headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(Integer headLineId);
    Result<List<HeadLine>> queryHeadLineList(HeadLine headLine, Integer pageNumber ,Integer pageSize);
}
