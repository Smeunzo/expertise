package com.commerce.services.user.utils;

import java.util.UUID;

public class UUIDGenerator {
    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase().substring(0,6);
    }
}
