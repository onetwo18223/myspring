package com.bhh.entity.bo;

import lombok.Data;

import java.util.Date;

@Data
public class HeadLine {
    private Long LineId;
    private String LineName;
    private String LineLink;
    private String LineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
