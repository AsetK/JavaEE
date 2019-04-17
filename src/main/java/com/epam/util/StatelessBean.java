package com.epam.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Stateless(mappedName = "someClassBean")
public class StatelessBean {

    @EJB
    private StatelessBeanForAsynch statelessBeanForAsynch;


    public void info()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Object info: " + this + "   Method info: " + System.currentTimeMillis());
    }


    public void testAsynch() //this method wouldn't wait execution of statelessBeanForAsynch.info() because it marked as @Asynchronous
    {
        statelessBeanForAsynch.info();
        System.out.println("testAsynch finished.");
    }

    public void testAsynchWithFuture()
    {
        Future future =  statelessBeanForAsynch.getDataFromDB(); // asynch execution
        System.out.println("testAsynchWithFuture middle"); //this code doesn't wait execution of asynch code
        try {
            LinkedList<Long> list = (LinkedList<Long>) future.get(); //but this code have to wait execution of asynch code, because it needs result from asynch code
//            LinkedList<Long> list = (LinkedList<Long>) future.get(2, TimeUnit.SECONDS); //defining waiting time
            System.out.println("AsynchFuture: " + list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("testAsynchWithFuture finished.");
    }

    @PostConstruct
    public void myInit()
    {
        System.out.println("Post constructor: " + this);
    }
}
