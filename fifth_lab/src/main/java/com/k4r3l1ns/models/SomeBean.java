package com.k4r3l1ns.models;

import com.k4r3l1ns.injection.custom_annotation.AutoInjectable;
import com.k4r3l1ns.models.interfaces.SomeInterface;
import com.k4r3l1ns.models.interfaces.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    private SomeInterface firstField;
    @AutoInjectable
    private SomeOtherInterface secondField;

    public void someFunction() {
        firstField.doSomething();
        secondField.doSomething();
    }
}
