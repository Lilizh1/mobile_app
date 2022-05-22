package com.he.week12_networkprogramming;

/**
 * Created by lenovo on 2022/5/22.
 */

public class Reader {
    private String reader_number;
    private String reader_name;
    private String reader_type;
    private String reader_phone;
    private String reader_password;
    private String reader_createtime;

    public String getReader_number() {
        return reader_number;
    }

    public void setReader_number(String reader_number) {
        this.reader_number = reader_number;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getReader_type() {
        return reader_type;
    }

    public void setReader_type(String reader_type) {
        this.reader_type = reader_type;
    }

    public String getReader_phone() {
        return reader_phone;
    }

    public void setReader_phone(String reader_phone) {
        this.reader_phone = reader_phone;
    }

    public String getReader_password() {
        return reader_password;
    }

    public void setReader_password(String reader_password) {
        this.reader_password = reader_password;
    }

    public String getReader_createtime() {
        return reader_createtime;
    }

    public void setReader_createtime(String reader_createtime) {
        this.reader_createtime = reader_createtime;
    }

    public Reader(String reader_number, String reader_name, String reader_type,
                  String reader_phone, String reader_password, String reader_createtime) {
        this.reader_number = reader_number;
        this.reader_name = reader_name;
        this.reader_type = reader_type;
        this.reader_phone = reader_phone;
        this.reader_password = reader_password;
        this.reader_createtime = reader_createtime;
    }

    public Reader() {
    }

    @Override
    public String toString() {
        return "读者{" +
                "编号:" + reader_number +
                "; 姓名：" + reader_name +
                "; 类别：" + reader_type +
                "; 电话" + reader_phone + "}\n";
    }
}
