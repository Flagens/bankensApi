package com.example.demo.utils;

public class ConfigurableLogger extends Logger implements Configurable {
    private String conf;

    @Override
    public void configure(String configuration) {
        this.conf = configuration;
    }

    public void logToFile(String msg) {
        System.out.println("I should log this to file from conf (" + this.conf + "): " + msg);
    }
}
