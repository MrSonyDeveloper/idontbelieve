package com.mrsony.idontbelieve.services.provider;

import com.mrsony.idontbelieve.services.QuestionService;
import com.mrsony.idontbelieve.services.impl.QuestionServiceImpl;

import java.util.HashMap;
import java.util.Map;

public final class ServiceProvider {

    private static final Map<Class<? extends Service>, Service> services = new HashMap<Class<? extends Service>, Service>();

    static {
        services.put(QuestionService.class, new QuestionServiceImpl());
    }

    private static final String NO_IMPLEMENTATION = "Не указана реализация для сервиса с типом '%s'! Добавьте ее в ServiceProvider!";

    public static <Type extends Service> Type getService(Class<Type> type) {
        Type service = (Type) services.get(type);
        if (service == null) {
            System.out.printf((NO_IMPLEMENTATION) + "%n", type.getName());
        }
        return service;
    }

}