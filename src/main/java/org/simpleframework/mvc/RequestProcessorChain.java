package org.simpleframework.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.render.ResultRender;
import org.simpleframework.mvc.render.impl.DefaultResultRender;
import org.simpleframework.mvc.render.impl.InternalErrorResultRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * 1.以责任链模式执行注册得请求处理器
 * 2.委派给特定的Render实例对处理后的结果进行渲染
 *
 * @author bhh
 */
@Slf4j
@Data
public class RequestProcessorChain {
    //RequestProcessor迭代器（迭代责任链模式中的四个RequestProcessor）
    private Iterator<RequestProcessor> requestProcessorIterator;
    //请求request
    private HttpServletRequest request;
    //响应response
    private HttpServletResponse response;
    //http请求方法
    private String requestMethod;
    //http请求路劲
    private String requestPath;
    //http响应状态码
    private int requestCode;
    //请求结果渲染器
    private ResultRender resultRender;

    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest request, HttpServletResponse response) {
        this.requestProcessorIterator = iterator;
        this.request = request;
        this.response = response;
        this.requestMethod = request.getMethod();
        this.requestPath = request.getPathInfo();
        this.requestCode = HttpServletResponse.SC_OK;

    }

    /**
     * 以责任链模式执行请求链
     */
    public void doRequestProcessorChain() {
//        1.通过迭代器遍历注册得请求处理器实例类列表
        try {
            while (requestProcessorIterator.hasNext()) {
//        2.直到某个请求处理器返回false
                if (!requestProcessorIterator.next().process(this)) {
                    break;
                }
            }
        } catch (Exception e) {
//        3.期间出现异常，交由内部异常渲染器处理
            this.resultRender = new InternalErrorResultRender();
        }
    }

    public void doRender() {
//        1.如果请求处理器实现类均为选择合适的渲染器，则使用默认得
        if(this.resultRender == null){
            this.resultRender = new DefaultResultRender();
        }
//        2.调用渲染器render对结果进行渲染
        try {
            this.resultRender.render(this);
        } catch (Exception e) {
            log.error("Exception in doRender() : ",e);
            throw new RuntimeException(e);
        }
    }
}
