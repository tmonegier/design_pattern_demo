package org.example.orm_demo.service;

public class LoggingService implements ILoggingService {

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
