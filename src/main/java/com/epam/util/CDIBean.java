package com.epam.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CDIBean {
    @Inject
    StatefulBean statefulBean;

    public void info(){
        System.out.println("CDI "+this);
        statefulBean.info();
    }
}
