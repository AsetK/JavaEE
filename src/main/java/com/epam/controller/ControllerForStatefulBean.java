package com.epam.controller;

import com.epam.util.StatefulBean;
import com.epam.util.StatelessBean;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ControllerForStatefulBean extends HttpServlet {

//    @EJB
//    StatefulBean statefulBean;

    @EJB
    StatelessBean statelessBean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        statefulBean.info();
//        Context context = null;
//
//
//        try {
//            context = new InitialContext();
//            StatefulBean statefulBean= (StatefulBean) context.lookup("java:app/JavaEE/StatefulBean");
//            statefulBean.info();
//
//
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }
}
