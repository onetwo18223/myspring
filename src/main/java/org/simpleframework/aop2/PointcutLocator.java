package org.simpleframework.aop2;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

public class PointcutLocator {
    /**
     * pointcut解析器，需要根据pointcutParser获取pointcutExpression
     */
    private PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
            //获取Aspectj所有的表达式的类型
            PointcutParser.getAllSupportedPointcutPrimitives()
    );

    /**
     * 表达式解析器
     */
    private PointcutExpression pointcutExpression;

    public PointcutLocator(String expression) {
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 判断传入的被代理类是否符合条件，即判断判断表达式是否符合条件（初筛）
     *
     * @param targetClass 目标类
     * @return 是否匹配
     */
    public boolean roughMatches(Class<?> targetClass) {
        //couldMatchJoinPointsInType只能校验within表达式
        //若是遇到其他表达式，如：execution等，直接返回true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * 判断传入的Method对象是否符合条件，即判断表达式是否符合条件（精筛）
     * @param method 目标方法
     * @return 是否匹配
     */
    public boolean accurateMatches(Method method){
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        //是否完全匹配
        if(shadowMatch.alwaysMatches()){
            return true;
        }
        return false;
    }

}
