package com.example.gur48256.myapplication.database;

public class DataItem {
    public String comm, first, last, phone, addr, gender;

    public  DataItem() {

    }

    public DataItem(String comm, String first, String last, String phone, String addr, String gender) {
        this.comm = comm;
        this.first = first;
        this.last = last;
        this.phone = phone;
        this.addr = addr;
        this.gender = gender;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
