package com.thc;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/19.
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
