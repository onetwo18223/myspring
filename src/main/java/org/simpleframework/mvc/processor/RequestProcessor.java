package org.simpleframework.mvc.processor;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * 请求处理器
 *
 * @author bhh
 */
public interface RequestProcessor {
    boolean process(RequestProcessorChain requestProcessChain) throws Exception;

}
