package com.epam.controller;

import com.epam.util.StatelessBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller extends HttpServlet {

    @Resource(mappedName = "java:jboss/datasources/OracleDS")
    private DataSource dataSource;

    @EJB
    private StatelessBean statelessBean;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = null;
        Long c = 0L;

        try {
            context = new InitialContext();

            //adding and getting objects from JNDI context.
            context.bind("counter", c);
            NamingEnumeration namesList = context.list("");
            while(namesList.hasMoreElements())
            {
                Object object = namesList.nextElement();
                System.out.println(object);
            }
            c = (Long) context.lookup("counter");
            System.out.println(c);
            c++;
            context.rebind("counter", c);


            //Getting DataSource from JNDI context and using it.
//            DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/OracleDS");
            System.out.println("datasource: " + dataSource);
            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS");
                ResultSet reesult =  preparedStatement.executeQuery();
                while (reesult.next())
                {
                    Long id = reesult.getLong(1);
                    System.out.println(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Getting stateless bean from JNDI context
//            StatelessBean statelessBean= (StatelessBean) context.lookup("java:app/JavaEE/StatelessBean");
//            statelessBean.info();

            // Getting stateless bean from JNDI context by @EJB
            statelessBean.info();

        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                context.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/page1.jsp");
        requestDispatcher.forward(request, response);
    }

}
