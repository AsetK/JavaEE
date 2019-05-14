package com.epam.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Stateful
@SessionScoped
public class StatefulBean implements Serializable{

    public StatefulBean()
    {
        System.out.println("Object:" + this);
    }

    public void info()
    {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Object info: " + this + "   Method info: " + System.currentTimeMillis());
        System.out.println("-----------------------------------------------------------------------------");
    }

}
