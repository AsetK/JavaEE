package com.epam.controller;

import com.epam.util.StatelessBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerForAsynchBean extends HttpServlet {

    @EJB
    StatelessBean statelessBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //statelessBean.testAsynch();
        statelessBean.testAsynchWithFuture();
    }
}
