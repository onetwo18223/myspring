package com.bhh.controller;

import com.bhh.service.combine.HeadLineShopCatagoryService;
import com.bhh.service.solo.HeadLineService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
@WebServlet("/hello")
@org.simpleframework.core.annotation.Controller
public class Controller extends HttpServlet {

    //@Autowired
    private HeadLineShopCatagoryService headLineShopCatagoryService;

    //@Autowired
    private com.bhh.service.solo.ShopCatagoryService ShopCatagoryService;

    @Autowired(value = "com.bhh.service.solo.Impl.AHeadLineServiceImpl")
    private HeadLineService headLineService;

    @Override
    public void init(ServletConfig servletConfig) {
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("slf4j");
        request.setAttribute("msg", "my 心情 is fine");
        //Result<HeadLineShopCatagory> headLineShopCatagory = headLineShopCatagoryService.getMainPageInfo();
        //request.setAttribute("headLineShopCatagory",headLineShopCatagory);
        //request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request,response);
        //HeadLine headLine = new HeadLine();
    }

    public void aspectTest(String str){
        log.debug("{},那么spring is good",str);
    }
}
