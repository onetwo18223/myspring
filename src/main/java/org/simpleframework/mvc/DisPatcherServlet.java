package org.simpleframework.mvc;

import lombok.SneakyThrows;
import org.simpleframework.aop1.AspectWeaver;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.DependencyInjector;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.JspRequestProcessor;
import org.simpleframework.mvc.processor.impl.PreRequestProcessor;
import org.simpleframework.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DisPatcherServlet extends HttpServlet {
    List<RequestProcessor> PROCESSOR = new ArrayList<>();
    @SneakyThrows
    @Override
    public void init(ServletConfig var1){
//        初始化容器
        BeanContainer beanContainer = BeanContainer.INSTANCE;
        beanContainer.loadBeans("com.bhh");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
//        初始化请求处理器责任链
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor(getServletContext()));
        PROCESSOR.add(new JspRequestProcessor(getServletContext()));
        PROCESSOR.add(new ControllerRequestProcessor());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       1.创建责任链对象实例
        RequestProcessorChain requestProcessChain = new RequestProcessorChain(PROCESSOR.iterator(), req, resp);
//       2.通过责任链对象来依次调用请求处理器对请求进行处理
        requestProcessChain.doRequestProcessorChain();
//       3.对处理结果进行渲染
        requestProcessChain.doRender();
    }
}
