package com.et.decode;

import java.util.Base64;

public class Decode {

    public static String decodeParam(String param) {
        byte[] decodedstr = Base64.getDecoder().decode(param);
        String str = new String(decodedstr);
        return str;
    }
    
}
