package com.example.demo.utils;

public class Logger {
    public void log(String msg){
        System.out.println(msg);
    }

    public void logToFile(String msg){
        System.out.println("I should log this to file: " + msg);
    }
}
