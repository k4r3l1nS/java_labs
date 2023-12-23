package com.k4r3l1ns.injection;

import com.k4r3l1ns.injection.custom_annotation.AutoInjectable;

import java.io.FileInputStream;
import java.util.Properties;

public class Injector {

    private static final String RESOURCE_FILEPATH = "/home/k4r3l1ns/Desktop/java_labs/fifth_lab/src/main/resources/";
    private static final String DEFAULT_PROPERTIES_FILENAME = "application.properties";

    /**
     * Инжектор для стандартного конфигурационного файла
     *
     * @param object Инициализируемый класс
     * @return Значение объекта после инициализации
     * @param <T> Шаблон
     */
    public static <T> T inject(T object) {
        return inject(object, DEFAULT_PROPERTIES_FILENAME);
    }

    /**
     * Инжектор для заданного конфигурационного файла
     *
     * @param object Инициализируемый класс
     * @param propertiesFilename Название конфигурационного файла
     * @return Значение объекта после инициализации
     * @param <T> Шаблон
     */
    public static <T> T inject(T object, String propertiesFilename) {

        try {
            FileInputStream input = new FileInputStream(RESOURCE_FILEPATH + propertiesFilename);

            Properties properties = new Properties();
            properties.load(input);

            Class<?> c = object.getClass();
            for (var field : c.getDeclaredFields()) {

                if (field.isAnnotationPresent(AutoInjectable.class)) {

                    var fieldType = field.getType();
                    String interfaceName = fieldType.getName();
                    String implementationClassName = properties.getProperty(interfaceName);
                    if (implementationClassName != null) {

                        var implementationInstance = Class.forName(implementationClassName).newInstance();
                        field.setAccessible(true);
                        field.set(object, implementationInstance);
                    }
                }
            }

            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
