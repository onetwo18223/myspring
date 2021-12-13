package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * 渲染请求结果
 *
 * @author bhh
 */
public interface ResultRender {
    //执行渲染
    void render(RequestProcessorChain requestProcessChain)throws Exception;
}
