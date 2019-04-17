package com.epam.util;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.Future;

@Stateless
public class StatelessBeanForAsynch {

    @Resource(mappedName = "java:jboss/datasources/OracleDS")
    private DataSource dataSource;


    @Asynchronous
    public void info() // must return void or Future
    {
        for(int i = 0; i < 5; i++)
        {
            System.out.println("StatelessBeanForAsynch.info - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Asynchronous
    public Future getDataFromDB()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedList<Long> list = new LinkedList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS");
            ResultSet reesult =  preparedStatement.executeQuery();
            while (reesult.next())
            {
                Long id = reesult.getLong(1);
                list.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AsyncResult asyncResult = new AsyncResult(list);
        System.out.println("getDataFromDB finished");
        return asyncResult;
    }
}
