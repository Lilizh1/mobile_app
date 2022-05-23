package com.he.week13_moretech;

import android.app.Application;

import java.io.StringReader;

/**
 * Created by lenovo on 2022/5/23.
 */

public class Myapplication extends Application {
    private String userName;
    private String orgName;

    @Override
    public void onCreate() {
        super.onCreate();
        setUserName("anonymous");
        setOrgName("unknown");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
