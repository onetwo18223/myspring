package org.simpleframework.aop2.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop2.PointcutLocator;

@Getter
@AllArgsConstructor
public class AspectInfo {
    private int orderInfo;
    private DefaultAspect aspectObj;
    private PointcutLocator pointcutLocator;
}
