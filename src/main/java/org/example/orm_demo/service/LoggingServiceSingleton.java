package org.example.orm_demo.service;

public class LoggingServiceSingleton implements ILoggingService {

    private static LoggingServiceSingleton instance;

    private LoggingServiceSingleton() {
    }

    public static LoggingServiceSingleton getInstance() {
        if (instance == null) {
            instance = new LoggingServiceSingleton();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }
}
