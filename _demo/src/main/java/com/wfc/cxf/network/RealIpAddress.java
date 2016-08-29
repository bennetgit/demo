package com.wfc.cxf.network;

import java.net.SocketException;

import com.wfc.cxf.utils.HttpUtils;

public class RealIpAddress {

    public static void main(String[] args) {
        try {
            System.out.println(HttpUtils.getRealIp());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
