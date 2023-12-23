package com.k4r3l1ns;

import com.k4r3l1ns.injection.Injector;
import com.k4r3l1ns.models.SomeBean;

public class Main {

    public static void main(String[] args) {

        System.out.println("\nТест со стандартным конфигурационным файлом \"application.properties\":\n");
        var someBean = Injector.inject(new SomeBean());
        someBean.someFunction();

        System.out.println("\n\nТест с заданным конфигурационным файлом \"application2.properties\":\n");
        someBean = Injector.inject(new SomeBean(), "application2.properties");
        assert someBean != null;
        someBean.someFunction();
    }
}