package com.ishansong.ops.datahub.property;

import ch.qos.logback.core.PropertyDefinerBase;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/*

public class CustomProperty extends PropertyDefinerBase {

    private  String fileName;

    public   CustomProperty(String fileName){
        this.fileName=fileName;
    }
    @Override
    public String getPropertyValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = sdf.format(new Date());
        try{
            InetAddress address = InetAddress.getLocalHost();
            str = address.getHostAddress() + "_" + str;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }



}*/