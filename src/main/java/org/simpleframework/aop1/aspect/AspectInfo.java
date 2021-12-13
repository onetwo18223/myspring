package org.simpleframework.aop1.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AspectInfo {
    private int orderInfo;
    private DefaultAspect aspectObj;
}
